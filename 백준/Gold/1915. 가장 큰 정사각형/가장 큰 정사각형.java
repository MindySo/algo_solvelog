import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringTokenizer st;
	static int N, M, max;
	static int[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		init();
		BFS();
		System.out.println(max * max);
	}

	static void init() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		max = 0;
		map = new int[N][M];
		visited = new boolean[N][M];

		String temp;
		for (int i = 0; i < N; i++) {
			temp = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = temp.charAt(j) - '0';
			}
		}
	}

	static void BFS() throws IOException {
		ArrayDeque<Integer> que = new ArrayDeque();
		que.add(0);

		int curr, r, c, minSquare;
		while (!que.isEmpty()) {
			curr = que.poll();
			r = curr / M;
			c = curr % M;

			if (!inside(r, c) || visited[r][c]) {
				continue;
			}

			visited[r][c] = true;
			if (map[r][c] != 0) {
				if (valid(r - 1, c - 1) && valid(r - 1, c) && valid(r, c - 1)) {
					minSquare = Math.min(map[r-1][c-1], Math.min(map[r-1][c], map[r][c-1]));
					map[r][c] = minSquare + 1;
				} else {
					map[r][c] = 1;
				}
				max = Math.max(max, map[r][c]);
			} else {
				map[r][c] = 0;
			}
			que.add((r + 1) * M + c);
			que.add(r * M + (c + 1));
		}
	}

	static boolean inside(int r, int c) {
		if (r >= 0 && r < N && c >= 0 && c < M) {
			return true;
		}
		return false;
	}

	static boolean valid(int r, int c) {
		if (inside(r, c) && map[r][c] != 0) {
			return true;
		}
		return false;
	}
}