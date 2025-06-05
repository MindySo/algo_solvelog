import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int M;
	static int N;
	static int[][] map;
	static boolean[][] visited;
	static int[][] canReach;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		visited = new boolean[M][N];
		canReach = new int[M][N];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				canReach[i][j] = -1;
			}
		}

		visited[0][0] = true;
		int ans = dfs(0, 0);

		System.out.println(ans);
	} // main

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };

	static int dfs(int r, int c) {
		if (r == M - 1 && c == N - 1) {
			return 1;
		}

		int roadCount = 0;
		int newR, newC;
		for (int d = 0; d < 4; d++) {
			newR = r + dr[d];
			newC = c + dc[d];

			if (newR >= 0 && newR < M && newC >= 0 && newC < N && map[newR][newC] < map[r][c]) {
				if (canReach[newR][newC] != -1) {
					roadCount += canReach[newR][newC];
					continue;
				}

				if (!visited[newR][newC] && map[newR][newC] < map[r][c]) {
					visited[newR][newC] = true;
					roadCount += dfs(newR, newC);
					visited[newR][newC] = false;
				}
			}
		}

		canReach[r][c] = roadCount;
		return roadCount;
	}
}
