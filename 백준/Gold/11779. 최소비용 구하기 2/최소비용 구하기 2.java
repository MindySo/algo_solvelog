import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static List<Edge>[] edges;
	static int[] dist;
	static int[] lastTransit;
	static final int INF = Integer.MAX_VALUE;

	static class Edge {
		int from, to, cost;

		Edge(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int M = Integer.parseInt(br.readLine().trim());

		edges = new List[N + 1];
		dist = new int[N + 1];
		lastTransit = new int[N + 1];
		Arrays.fill(dist, INF);
		for (int i = 0; i < N + 1; i++) {
			edges[i] = new ArrayList<>();
		}

		StringTokenizer st;
		int f, t, c;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			f = Integer.parseInt(st.nextToken());
			t = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			edges[f].add(new Edge(f, t, c));
		}

		st = new StringTokenizer(br.readLine());
		int start = Integer.parseInt(st.nextToken());
		int end = Integer.parseInt(st.nextToken());
		dist[start] = 0;
		
		dijkstra(start);
		
		int ans = 0;
		Stack<Integer> ansStk = new Stack<>();
		
		int transit = end;
		ansStk.add(end);
		while(true) {
			transit	= lastTransit[transit];
			ansStk.add(transit);
			if(transit == start) {
				break;
			}
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(dist[end]).append("\n");
		sb.append(ansStk.size()).append("\n");
		while(!ansStk.isEmpty()) {
			sb.append(ansStk.pop()).append(" ");
		}
		System.out.println(sb);
	}	// main
	
	static void dijkstra(int start) {
		PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.cost - o2.cost;
			}
		});
		for(Edge e : edges[start]) {
			pq.add(e);
		}
		
		Edge now;
		int newCost;
		while(!pq.isEmpty()) {
			now = pq.poll();
			newCost = dist[now.from] + now.cost;
			if(newCost < dist[now.to]) {
				dist[now.to] = newCost;
				lastTransit[now.to] = now.from;
				
				for(Edge e : edges[now.to]) {
					pq.add(e);
				}
			}
		}
	}	// dijkstra
}
