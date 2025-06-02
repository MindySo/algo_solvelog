import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static class Road{
		int to;
		int time;
		
		public Road(int to, int time) {
			this.to = to;
			this.time = time;
		}
	}
	
	static List<Road>[] map;
	static int[][] dist;
	public static void main(String[] args) throws IOException {
		int N, M, X;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());

		map = new List[N + 1];
		dist = new int[N + 1][N + 1];
		for(int i = 1 ; i < N + 1 ; i++) {
			map[i] = new ArrayList<>();
			Arrays.fill(dist[i], Integer.MAX_VALUE);
			dist[i][i] = 0;
		}

		int from, to, time;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			time = Integer.parseInt(st.nextToken());
			map[from].add(new Road(to, time));
		}

		int max = 0;
		
		dijkstra(X);
		for (int i = 1; i < N + 1; i++) {
			if(i == X) {
				continue;
			}
			
			dijkstra(i);
			
			if (dist[i][X] + dist[X][i] > max) {
				max = dist[i][X] + dist[X][i];
			}
		}
		
		System.out.println(max);
	}	// main()
	
	static void dijkstra(int start) {
		PriorityQueue<Road> que = new PriorityQueue<>(new Comparator<Road>() {
			@Override
			public int compare(Road o1, Road o2) {
				return o1.time - o2.time;
			}
		});

		for(Road r : map[start]) {
			if(r.time < dist[start][r.to]) {
				dist[start][r.to] = r.time;
				que.add(r);
			}
		}

		while (!que.isEmpty()) {
			Road now = que.poll();

			if (now.time > dist[start][now.to]) {
				continue;
			}
			
			dist[start][now.to] = now.time;
			for(Road next : map[now.to]) {
				int newTime = dist[start][now.to] + next.time;
				if(newTime < dist[start][next.to]) {
					dist[start][next.to] = newTime;
					que.add(new Road(next.to, newTime));
				}
			}
		}
	}	// dijkstra()
}