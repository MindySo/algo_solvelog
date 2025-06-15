import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static List<Integer> cams;
	static List<Integer> fives;
	static int ans;

	public static void main(String[] args) throws IOException {
		getInput();

		if (!fives.isEmpty()) {
			five();
		}
		
		ans = count();
		if (!cams.isEmpty()) {
			DFS(0);
		}
		System.out.println(ans);
	}

	static void getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cams = new ArrayList<>();
		fives = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] >= 1 && map[i][j] <= 4) {
					cams.add(i * M + j);
				} else if (map[i][j] == 5) {
					fives.add(i * M + j);
				}
			}
		}
	} // getInput()

	static void DFS(int camIdx) {
		if (camIdx == cams.size()) {
			int cnt = count();
			if (ans > cnt) {
				ans = cnt;
			}
			return;
		}

		int cam = cams.get(camIdx);
		int camNum = map[cam / M][cam % M];
		
		// watch(cam, camIdx, 방향 개수, 방향 사이 공백)
		if (camNum == 1) {
			watch(cam, camIdx, 1, 1);	// 개수 1(상), 공백 1
		} else if (camNum == 2) {
			watch(cam, camIdx, 3, 2);	// 개수 3(상, (우), 하), 공백 2
		} else if (camNum == 3) {
			watch(cam, camIdx, 2, 1);	// 개수 2(상, 우), 공백 1
		} else {
			watch(cam, camIdx, 3, 1);	// 개수 3(상, 우, 하), 공백 1
		}
	}

	// 시계방향 순
	static int[] dr = { -1, 0, 1, 0, -1, 0};
	static int[] dc = { 0, 1, 0, -1, 0, 1 };

	static void watch(int cam, int camIdx, int arrows, int gap) {
		List<int[]> backTrack = new ArrayList<>();
		int r, c, newR, newC;
		r = cam / M;
		c = cam % M;

		for (int rotate = 0; rotate < 4; rotate++) {
			for (int dir = 0; dir < arrows; dir += gap) {
				int d = rotate + dir;
				newR = r + dr[d];
				newC = c + dc[d];
				while (newR >= 0 && newR < N && newC >= 0 && newC < M) {
					if (map[newR][newC] == 6) {
						break;
					}

					backTrack.add(new int[] { newR, newC, map[newR][newC] });

					if (map[newR][newC] == 0) {
						map[newR][newC] = -1;
					}

					newR += dr[d];
					newC += dc[d];
				} // while()
			}

			DFS(camIdx + 1);
			backTracking(backTrack);
			backTrack.clear();
		}
	}	// watch()

	static void five() {
		int r, c, newR, newC;
		for (int cam : fives) {
			r = cam / M;
			c = cam % M;

			for (int d = 0; d < 4; d++) {
				newR = r + dr[d];
				newC = c + dc[d];
				while (newR >= 0 && newR < N && newC >= 0 && newC < M) {
					if (map[newR][newC] == 6) {
						break;
					}

					if (map[newR][newC] == 0) {
						map[newR][newC] = -1;
					}

					newR += dr[d];
					newC += dc[d];
				}	// while()
			}
		}
	}	// five()

	static void backTracking(List<int[]> backTrack) {
		int r, c, state;
		for (int[] back : backTrack) {
			r = back[0];
			c = back[1];
			state = back[2];

			map[r][c] = state;
		}
	}	// backTracking()

	static int count() {
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}	// count()
}
