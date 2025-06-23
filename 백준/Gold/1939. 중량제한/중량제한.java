import java.io.*;
import java.util.*;

public class Main {
	static class Bridge {
		int to;
		long w;

		Bridge(int to, long w) {
			this.to = to;
			this.w = w;
		}
	}

	static List<Bridge>[] bridges;
	static boolean[] visited;
	static int start, dest;

	static boolean canCarry(long limit) {
		Arrays.fill(visited, false);
		Queue<Integer> q = new ArrayDeque<>();
		q.add(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			int cur = q.poll();
			if (cur == dest)
				return true;
			for (Bridge b : bridges[cur]) {
				if (!visited[b.to] && b.w >= limit) {
					visited[b.to] = true;
					q.add(b.to);
				}
			}
		}
		return false;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		bridges = new ArrayList[N + 1];
		for (int i = 1; i <= N; i++)
			bridges[i] = new ArrayList<>();

		long low = Long.MAX_VALUE, high = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long w = Long.parseLong(st.nextToken());
			bridges[a].add(new Bridge(b, w));
			bridges[b].add(new Bridge(a, w));
			low = Math.min(low, w);
			high = Math.max(high, w);
		}

		st = new StringTokenizer(br.readLine());
		start = Integer.parseInt(st.nextToken());
		dest = Integer.parseInt(st.nextToken());

		visited = new boolean[N + 1];
		long left = low, right = high, answer = low;
		while (left <= right) {
			long mid = (left + right) / 2;
			if (canCarry(mid)) {
				answer = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		System.out.println(answer);
	}
}