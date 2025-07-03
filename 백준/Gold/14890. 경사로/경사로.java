import java.io.*;
import java.util.*;

public class Main {
	static int N, L;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;
		for (int i = 0; i < N; i++) {
			check(0, i, 1, 1, false);
			check(1, 1, i, 1, false);
		}

		System.out.println(ans);
	}// main();

	static int ans = 0;
	static int[] dr = { 0, 1 };
	static int[] dc = { 1, 0 };

	static void check(int dir, int r, int c, int length, boolean using) {
		if (r == N || c == N) {
			if (!using) {
				ans++;
			}
			return;
		}

		int prev = map[r - dr[dir]][c - dc[dir]];
		if (prev == map[r][c]) {
			length++;
		} else if (prev == map[r][c] + 1 && !using) {
			using = true;
			length = 1;
		} else if (prev == map[r][c] - 1 && !using && length >= L) {
			length = 1;
		} else {
			return;
		}

		if (length >= L && using) {
			length = 0;
			using = false;
		}

		check(dir, r + dr[dir], c + dc[dir], length, using);
	}
}
