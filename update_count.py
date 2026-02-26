import os
import re
import subprocess
import urllib.parse
from datetime import datetime, timedelta


BAEKJOON_LEVELS = [
    ("Silver", "Silver"),
    ("Gold", "Gold"),
    ("Platinum", "Platinum"),
]
PROGRAMMERS_LEVELS = [
    ("1", "Lv. 1"),
    ("2", "Lv. 2"),
    ("3", "Lv. 3"),
    ("4", "Lv. 4"),
    ("5", "Lv. 5"),
]

GITHUB_REPO_URL = "https://github.com/MindySo/algo_solvelog/tree/main"


def count_problems(base_dir):
    counts = {}

    baekjoon_dir = os.path.join(base_dir, "백준")
    for folder, label in BAEKJOON_LEVELS:
        level_dir = os.path.join(baekjoon_dir, folder)
        if os.path.isdir(level_dir):
            counts[("백준", label)] = len([
                d for d in os.listdir(level_dir)
                if os.path.isdir(os.path.join(level_dir, d))
            ])

    programmers_dir = os.path.join(base_dir, "프로그래머스")
    for folder, label in PROGRAMMERS_LEVELS:
        level_dir = os.path.join(programmers_dir, folder)
        if os.path.isdir(level_dir):
            counts[("프로그래머스", label)] = len([
                d for d in os.listdir(level_dir)
                if os.path.isdir(os.path.join(level_dir, d))
            ])

    return counts


def get_problem_data_by_date(base_dir):
    """git log에서 날짜별 추가된 문제 디렉토리 데이터 추출.
    반환: {날짜: {'problems': set((platform, level, prob_name)), 'last_problem': tuple}}
    """
    result = subprocess.run(
        ['git', '-c', 'core.quotepath=false', 'log',
         '--pretty=format:---COMMIT:%ad---',
         '--date=format:%Y.%m.%d',
         '--diff-filter=A', '--name-only',
         '--', '백준/', '프로그래머스/'],
        capture_output=True, text=True, encoding='utf-8', cwd=base_dir
    )

    if result.returncode != 0:
        return {}

    data_by_date = {}
    current_date = None
    last_problem_locked = False  # 더 최신 커밋에서 이미 last_problem이 확정됐는지 여부

    for line in result.stdout.split('\n'):
        line = line.strip()
        if not line:
            continue

        if line.startswith('---COMMIT:') and line.endswith('---'):
            new_date = line[10:-3]
            if new_date not in data_by_date:
                data_by_date[new_date] = {'problems': set(), 'last_problem': None}
                last_problem_locked = False
            else:
                # 같은 날 더 오래된 커밋 → last_problem은 이미 최신 커밋에서 설정됨
                last_problem_locked = True
            current_date = new_date

        elif current_date and '/' in line:
            parts = line.split('/')
            if len(parts) >= 3 and parts[0] in ('백준', '프로그래머스'):
                prob_key = (parts[0], parts[1], parts[2])
                data_by_date[current_date]['problems'].add(prob_key)
                if not last_problem_locked and data_by_date[current_date]['last_problem'] is None:
                    data_by_date[current_date]['last_problem'] = prob_key

    return data_by_date


def calculate_streak(data_by_date):
    """연속 풀이 streak 계산.
    반환: (streak_count, start_str, end_str)  — 끊겼으면 (0, None, None)
    """
    if not data_by_date:
        return 0, None, None

    def fmt(dt):
        return f'{dt.year}.{dt.month}.{dt.day}'

    dates = sorted(data_by_date.keys(), reverse=True)
    date_objs = [datetime.strptime(d, '%Y.%m.%d') for d in dates]

    today = datetime.now().replace(hour=0, minute=0, second=0, microsecond=0)
    latest = date_objs[0]

    # 최근 푼 날이 오늘 또는 어제여야 streak 유지
    if (today - latest).days > 1:
        return 0, None, None

    streak = 1
    current = latest
    for i in range(1, len(date_objs)):
        if (current - date_objs[i]).days == 1:
            streak += 1
            current = date_objs[i]
        else:
            break

    return streak, fmt(current), fmt(latest)


def _get_problem_num(prob_name):
    m = re.match(r'^(\d+)', prob_name)
    return m.group(1) if m else ''


def _get_problem_url(platform, prob_name):
    num = _get_problem_num(prob_name)
    if platform == '백준':
        return f'https://www.acmicpc.net/problem/{num}', f'b.{num}'
    else:
        return f'https://school.programmers.co.kr/learn/courses/30/lessons/{num}', f'p.{num}'


def _get_github_dir_url(platform, level, prob_name):
    path = f'{platform}/{level}/{prob_name}'
    encoded = urllib.parse.quote(path, safe='/')
    return f'{GITHUB_REPO_URL}/{encoded}'


def build_stats_section(data_by_date, streak, start_date, end_date):
    """streak 표시 + 최근 7일 문제 테이블 HTML 생성"""

    if streak > 0 and start_date and end_date:
        streak_html = f'<b>🔥Current Streak</b><br><h3>{streak}일째</h3><sub>{start_date} ~ {end_date}</sub>'
    else:
        streak_html = '<b>🔥Current Streak</b><br><h3>0일째</h3>'

    # 문제 푼 날만, 최근 7일, 오래된 순(왼→오)
    sorted_dates = sorted(data_by_date.keys(), reverse=True)[:7][::-1]

    if not sorted_dates:
        return f'<div align="center">{streak_html}</div>'

    header_row = '<th align="center">날짜</th>'
    count_row = '<th align="center">문제 수</th>'
    link_row = '<th align="center">문제 링크</th>'
    dir_row = '<th align="center">풀이 링크</th>'

    for date in sorted_dates:
        dt = datetime.strptime(date, '%Y.%m.%d')
        date_label = dt.strftime('%y.%m.%d')

        info = data_by_date[date]
        count = len(info['problems'])
        last = info['last_problem']

        header_row += f'<th align="center">{date_label}</th>'
        count_row += f'<td align="center">{count}</td>'

        if last:
            platform, level, prob_name = last
            prob_url, prob_text = _get_problem_url(platform, prob_name)
            dir_url = _get_github_dir_url(platform, level, prob_name)
            link_row += f'<td align="center"><a href="{prob_url}">{prob_text}</a></td>'
            dir_row += f'<td align="center"><a href="{dir_url}">🔗</a></td>'
        else:
            link_row += '<td align="center">-</td>'
            dir_row += '<td align="center">-</td>'

    weekly_table = (
        f'<table>'
        f'<thead><tr>{header_row}</tr></thead>'
        f'<tbody>'
        f'<tr>{count_row}</tr>'
        f'<tr>{link_row}</tr>'
        f'<tr>{dir_row}</tr>'
        f'</tbody>'
        f'</table>'
    )

    lines = [
        '<table><tbody><tr>',
        f'<td align="center" valign="middle" width="160">{streak_html}</td>',
        f'<td>{weekly_table}</td>',
        '</tr></tbody></table>',
    ]
    return '\n'.join(lines)


def build_section(counts):
    total = sum(counts.values())

    baekjoon = [(label, counts.get(("백준", label), 0)) for _, label in BAEKJOON_LEVELS]
    programmers = [(label, counts.get(("프로그래머스", label), 0)) for _, label in PROGRAMMERS_LEVELS]

    lines = []

    # 총 문제 수 헤더
    lines.append('')
    lines.append(f'### 해결한 문제 : {total} 개')
    lines.append('')

    # 테이블
    lines.append('<table>')
    lines.append('  <thead>')
    lines.append('    <tr>')
    lines.append('      <th width="150"><div align="center">플랫폼</div></th>')
    lines.append('      <th width="150"><div align="center">난이도</div></th>')
    lines.append('      <th width="100"><div align="center">문제 수</div></th>')
    lines.append('    </tr>')
    lines.append('  </thead>')
    lines.append('  <tbody>')

    # 백준
    for i, (level, count) in enumerate(baekjoon):
        lines.append('    <tr>')
        if i == 0:
            lines.append(f'      <td rowspan="{len(baekjoon)}" align="center" valign="middle"><b>백준</b></td>')
        lines.append(f'      <td align="center">{level}</td>')
        lines.append(f'      <td align="center">{count}</td>')
        lines.append('    </tr>')

    # 프로그래머스
    for i, (level, count) in enumerate(programmers):
        lines.append('    <tr>')
        if i == 0:
            lines.append(f'      <td rowspan="{len(programmers)}" align="center" valign="middle"><b>프로그래머스</b></td>')
        lines.append(f'      <td align="center">{level}</td>')
        lines.append(f'      <td align="center">{count}</td>')
        lines.append('    </tr>')

    # 총합
    lines.append('    <tr>')
    lines.append(f'      <td colspan="2" align="center"><b>총합</b></td>')
    lines.append(f'      <td align="center"><b>{total}</b></td>')
    lines.append('    </tr>')

    lines.append('  </tbody>')
    lines.append('</table>')
    lines.append('')

    return '\n'.join(lines)


def _replace_section(content, start_marker, end_marker, new_section):
    start = content.index(start_marker)
    end = content.index(end_marker) + len(end_marker)
    return content[:start] + start_marker + "\n" + new_section + "\n" + end_marker + content[end:]


def update_readme(base_dir):
    readme_path = os.path.join(base_dir, "README.md")
    with open(readme_path, "r", encoding="utf-8") as f:
        content = f.read()

    # COUNT 섹션 업데이트
    counts = count_problems(base_dir)
    count_section = build_section(counts)
    content = _replace_section(content, "<!-- COUNT_START -->", "<!-- COUNT_END -->", count_section)

    # STATS 섹션 업데이트 (streak + 주간 테이블)
    data_by_date = get_problem_data_by_date(base_dir)
    streak, start_date, end_date = calculate_streak(data_by_date)
    stats_section = build_stats_section(data_by_date, streak, start_date, end_date)
    content = _replace_section(content, "<!-- STATS_START -->", "<!-- STATS_END -->", stats_section)

    with open(readme_path, "w", encoding="utf-8") as f:
        f.write(content)

    total = sum(counts.values())
    print(f"README updated: {total} problems total, {streak}일 streak")


if __name__ == "__main__":
    base_dir = os.path.dirname(os.path.abspath(__file__))
    update_readme(base_dir)
