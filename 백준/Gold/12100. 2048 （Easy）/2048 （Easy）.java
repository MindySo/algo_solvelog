import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int maxVal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		maxVal = 0;
		DFS(0, map);

		System.out.println(maxVal);
	}// main()

	static void DFS(int depth, int[][] map) {
		if (depth == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					maxVal = Math.max(maxVal, map[i][j]);
				}
			}
			return;
		}

		DFS(depth + 1, left(map));
		DFS(depth + 1, right(map));
		DFS(depth + 1, up(map));
		DFS(depth + 1, down(map));
	}// DFS()

	static int[][] left(int[][] map) {
		int[][] newMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			int j = 0;
			int idx = 0;
			boolean merged = false;
			while (idx < N) {
				if (map[i][idx] == 0) {
					idx++;
					continue;
				} else if (j >= 1 && !merged && newMap[i][j - 1] == map[i][idx]) {
					newMap[i][j - 1] = map[i][idx++] * 2;
					merged = true;
				} else {
					newMap[i][j++] = map[i][idx++];
					merged = false;
				}
			}
			while (j < N) {
				newMap[i][j++] = 0;
			}
		}
		return newMap;
	}// left()

	static int[][] right(int[][] map) {
		int[][] newMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			int j = N - 1;
			int idx = N - 1;
			boolean merged = false;
			while (idx >= 0) {
				if (map[i][idx] == 0) {
					idx--;
					continue;
				} else if (j < N - 1 && !merged && newMap[i][j + 1] == map[i][idx]) {
					newMap[i][j + 1] = map[i][idx--] * 2;
					merged = true;
				} else {
					newMap[i][j--] = map[i][idx--];
					merged = false;
				}
			}
			while (j >= 0) {
				newMap[i][j--] = 0;
			}
		}
		return newMap;
	}// right()

	static int[][] up(int[][] map) {
		int[][] newMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			int j = 0;
			int idx = 0;
			boolean merged = false;
			while (idx < N) {
				if (map[idx][i] == 0) {
					idx++;
					continue;
				} else if (j >= 1 && !merged && newMap[j - 1][i] == map[idx][i]) {
					newMap[j - 1][i] = map[idx++][i] * 2;
					merged = true;
				} else {
					newMap[j++][i] = map[idx++][i];
					merged = false;
				}
			}
			while (j < N) {
				newMap[j++][i] = 0;
			}
		}
		return newMap;
	}// up()

	static int[][] down(int[][] map) {
		int[][] newMap = new int[N][N];
		for (int i = 0; i < N; i++) {
			int j = N - 1;
			int idx = N - 1;
			boolean merged = false;
			while (idx >= 0) {
				if (map[idx][i] == 0) {
					idx--;
					continue;
				} else if (j < N - 1 && !merged && newMap[j + 1][i] == map[idx][i]) {
					newMap[j + 1][i] = map[idx--][i] * 2;
					merged = true;
				} else {
					newMap[j--][i] = map[idx--][i];
					merged = false;
				}
			}
			while (j >= 0) {
				newMap[j--][i] = 0;
			}
		}
		return newMap;
	}// down()
}