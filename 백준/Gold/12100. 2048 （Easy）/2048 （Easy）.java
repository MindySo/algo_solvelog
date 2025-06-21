import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	static int N;
	static BigInteger[][] map;
	static BigInteger maxVal;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		N = Integer.parseInt(br.readLine().trim());
		map = new BigInteger[N][N];
		int input;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				input = Integer.parseInt(st.nextToken());
				map[i][j] = BigInteger.valueOf(input);
			}
		}

		maxVal = BigInteger.ZERO;
		DFS(0);

		System.out.println(maxVal);

	}// main()

	static void DFS(int depth) {
		if (depth == 5) {
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					maxVal = maxVal.max(map[i][j]);
				}
			}
			return;
		}

		BigInteger[][] backTrackMap = new BigInteger[N][N];
		for (int i = 0; i < N; i++) {
			backTrackMap[i] = Arrays.copyOf(map[i], N);
		}

		left(depth);
		backTrack(backTrackMap);

		right(depth);
		backTrack(backTrackMap);

		up(depth);
		backTrack(backTrackMap);

		down(depth);
		backTrack(backTrackMap);
	}// DFS()

	static void backTrack(BigInteger[][] backTrackMap) {
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.copyOf(backTrackMap[i], N);
		}
	}// backTrack()

	static BigInteger curr;
	static boolean merged;
	
	static void left(int depth) {
		for (int i = 0; i < N; i++) {
			int j = 0;
			int idx = 0;
			merged = false;
			while (idx < N) {
				if (map[i][idx].equals(BigInteger.ZERO)) {
					idx++;
					continue;
				} else if (j >= 1 && !merged && map[i][j - 1].equals(map[i][idx])) {
					map[i][j - 1] = map[i][idx++].multiply(BigInteger.TWO);
					merged = true;
				} else {
					map[i][j++] = map[i][idx++];
					merged = false;
				}
			}
			while (j < N) {
				map[i][j++] = BigInteger.ZERO;
			}
		}
		DFS(depth + 1);
	}// left()

	static void right(int depth) {
		for (int i = 0; i < N; i++) {
			int j = N - 1;
			int idx = N - 1;
			merged = false;
			while (idx >= 0) {
				if (map[i][idx].equals(BigInteger.ZERO)) {
					idx--;
					continue;
				} else if (j < N - 1 && !merged && map[i][j + 1].equals(map[i][idx])) {
					map[i][j + 1] = map[i][idx--].multiply(BigInteger.TWO);
					merged = true;
				} else {
					map[i][j--] = map[i][idx--];
					merged = false;
				}
			}
			while (j >= 0) {
				map[i][j--] = BigInteger.ZERO;
			}
		}
		DFS(depth + 1);
	}// right()

	static void up(int depth) {
		for (int i = 0; i < N; i++) {
			int j = 0;
			int idx = 0;
			merged = false;
			while (idx < N) {
				if (map[idx][i].equals(BigInteger.ZERO)) {
					idx++;
					continue;
				} else if (j >= 1 && !merged && map[j - 1][i].equals(map[idx][i])) {
					map[j - 1][i] = map[idx++][i].multiply(BigInteger.TWO);
					merged = true;
				} else {
					map[j++][i] = map[idx++][i];
					merged = false;
				}
			}
			while (j < N) {
				map[j++][i] = BigInteger.ZERO;
			}
		}
		DFS(depth + 1);
	}// up()

	static void down(int depth) {
		for (int i = 0; i < N; i++) {
			int j = N - 1;
			int idx = N - 1;
			merged = false;
			while (idx >= 0) {
				if (map[idx][i].equals(BigInteger.ZERO)) {
					idx--;
					continue;
				} else if (j < N - 1 && !merged && map[j + 1][i].equals(map[idx][i])) {
					map[j + 1][i] = map[idx--][i].multiply(BigInteger.TWO);
					merged = true;
				} else {
					map[j--][i] = map[idx--][i];
					merged = false;
				}
			}
			while (j >= 0) {
				map[j--][i] = BigInteger.ZERO;
			}
		}
		DFS(depth + 1);
	}// down()
}