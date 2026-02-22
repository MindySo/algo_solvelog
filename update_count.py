import os


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


def build_section(counts):
    total = sum(counts.values())

    baekjoon = [(label, counts.get(("백준", label), 0)) for _, label in BAEKJOON_LEVELS]
    programmers = [(label, counts.get(("프로그래머스", label), 0)) for _, label in PROGRAMMERS_LEVELS]

    lines = []

    # 총 문제 수 헤더
    lines.append('')
    lines.append(f'### 해결한 문제 : {total} 개')
    lines.append('')
    lines.append(f'<sub>🔃 updated by GitHub Actions</sub>')
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


def update_readme(base_dir):
    readme_path = os.path.join(base_dir, "README.md")
    with open(readme_path, "r", encoding="utf-8") as f:
        content = f.read()

    counts = count_problems(base_dir)
    section = build_section(counts)

    start_marker = "<!-- COUNT_START -->"
    end_marker = "<!-- COUNT_END -->"

    start = content.index(start_marker)
    end = content.index(end_marker) + len(end_marker)

    new_content = content[:start] + start_marker + "\n" + section + "\n" + end_marker + content[end:]

    with open(readme_path, "w", encoding="utf-8") as f:
        f.write(new_content)

    total = sum(counts.values())
    print(f"README updated: {total} problems total")


if __name__ == "__main__":
    base_dir = os.path.dirname(os.path.abspath(__file__))
    update_readme(base_dir)
