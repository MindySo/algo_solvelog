import java.io.*;
import java.util.*;

public class Main {
	static int T, N, K, W;
	static int[] time;
	static int[] addedTime;
	static int[] checked;
	static boolean[] road;
	static List<Integer> root;
	static List<Integer>[] from;
	static List<Integer>[] to;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			getInput(br);
			getRoad();
			addTime();

			System.out.println(addedTime[W]);
		}
	}// main();

	static void getInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		time = new int[N + 1];
		addedTime = new int[N + 1];
		checked = new int[N + 1];
		road = new boolean[N + 1];
		root = new ArrayList<>();
		from = new List[N + 1];
		to = new List[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			time[i] = Integer.parseInt(st.nextToken());
			addedTime[i] = time[i];
			from[i] = new ArrayList<>();
			to[i] = new ArrayList<>();
		}

		int f, t;
		for (int i = 1; i <= K; i++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			from[f].add(t);
			to[t].add(f);
		}
		W = Integer.parseInt(br.readLine().trim());
	}// getInput()

	// W로 가는 경로로만 범위 축소
	static void getRoad() {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		que.add(W);
		int curr;
		while (!que.isEmpty()) {
			curr = que.poll();
			if (!road[curr]) {
				road[curr] = true;
				if (to[curr].size() == 0) {
					root.add(curr);
				} else {
					for (int f : to[curr]) {
						que.add(f);
					}
				}
			}
		}
	}// getRoad()

	static void addTime() {
		ArrayDeque<Integer> que = new ArrayDeque<>();
		for (int r : root) {
			que.add(r);
		}

		int curr;
		while (!que.isEmpty()) {
			curr = que.poll();
			for (int f : to[curr]) {
				addedTime[curr] = Math.max(addedTime[curr], addedTime[f] + time[curr]);
			}
			for (int t : from[curr]) {
				if (++checked[t] == to[t].size() && road[t]) {
					que.add(t);
				}
			}
		}
	}// addTime()
}
