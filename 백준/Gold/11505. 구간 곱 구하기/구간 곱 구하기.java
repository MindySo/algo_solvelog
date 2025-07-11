import java.io.*;
import java.util.*;

public class Main {
	static BufferedReader br;
	static StringBuilder sb;
	static int N, M, K, powerVal, leafStart;
	static long[] tree;
	static final int INF = 1_000_000_007;

	public static void main(String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		sb = new StringBuilder();
		getInput();

		for (int i = 0; i < M + K; i++) {
			getCommand();
		}
		System.out.println(sb);
	}

	static void getInput() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		powerVal = 0;
		while (Math.pow(2, powerVal) < N) {
			powerVal++;
		}
		tree = new long[(int) Math.pow(2, powerVal + 1)];
		leafStart = (int) Math.pow(2, powerVal);

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			tree[leafStart + i] = Long.parseLong(st.nextToken()) % INF;
		}

		for (int i = leafStart + N; i < tree.length; i++) {
			tree[i] = 1;
		}

		for (int i = tree.length - 1; i >= 3; i -= 2) {
			tree[i / 2] = tree[i] * tree[i - 1] % INF;
		}
	}

	static void getCommand() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int command = Integer.parseInt(st.nextToken());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());

		if (command == 1) {
			changeNum(--a, b);
		} else {
			getMulti(--a, --b);
		}
	}

	static void changeNum(int a, int b) {
		a += leafStart;
		tree[a] = b;

		while (a >= 1) {
			if (a % 2 == 0) {
				tree[a / 2] = tree[a] * tree[a + 1] % INF;
			} else {
				tree[a / 2] = tree[a] * tree[a - 1] % INF;
			}
			a /= 2;
		}
	}

	static void getMulti(int a, int b) {
		a += leafStart;
		b += leafStart;
		long val = 1;

		while (a <= b) {
			if (a == b) {
				val = val * tree[a] % INF;
				break;
			}

			if (a % 2 == 1) {
				val = val * tree[a] % INF;
				a++;
			}
			a /= 2;

			if (b % 2 == 0) {
				val = val * tree[b] % INF;
				b--;
			}
			b /= 2;
		}

		sb.append(val).append("\n");
	}
}