import java.io.*;
import java.util.*;

public class Main {
	static class Road {
		int next;
		int length;

		Road(int next, int length) {
			this.next = next;
			this.length = length;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());

		int[] items = new int[N + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			items[i] = Integer.parseInt(st.nextToken());
		}

		List<Road>[] roads = new List[N + 1];
		for (int i = 0; i <= N; i++) {
			roads[i] = new ArrayList<>();
		}

		int n1, n2, l;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			n1 = Integer.parseInt(st.nextToken());
			n2 = Integer.parseInt(st.nextToken());
			l = Integer.parseInt(st.nextToken());
			roads[n1].add(new Road(n2, l));
			roads[n2].add(new Road(n1, l));
		}

		int[] dij = new int[N + 1];
		PriorityQueue<Road> que = new PriorityQueue<>(new Comparator<Road>() {

			@Override
			public int compare(Road o1, Road o2) {
				return o1.length - o2.length;
			}
		});

		int max = -1;
		for (int i = 1; i <= N; i++) {
			Arrays.fill(dij, Integer.MAX_VALUE);
			dij[i] = 0;
			que.add(new Road(i, 0));
			
			Road curr;
			while (!que.isEmpty()) {
				curr = que.poll();
				for (Road r : roads[curr.next]) {
					int nextDist = dij[curr.next] + r.length;
					if(nextDist <= dij[r.next]) {
						dij[r.next] = nextDist;
						que.add(new Road(r.next, nextDist));
					}
				}
			}
			
			int cnt = 0;
			for(int j = 1 ; j <= N ; j++) {
				if(dij[j] <= M) {
					cnt += items[j];
				}
			}
			max = Math.max(max, cnt);
		}

		System.out.println(max);
	}
}
