import java.io.*;
import java.util.*;

public class Main {
	static int N, E;
	static int[] map;
	static List<Road>[] roads;
	static int v1, v2, vLength;
	
	static class Road{
		int next;
		int length;
		Road(int next, int length){
			this.next = next;
			this.length = length;
		}
	}
	
	public static void main(String[] args) throws IOException {
		init();
		
		int route1 = -1, route2 = -1;

		int oneToV1 = dijkstra(1, v1);
		int v1ToV2 = dijkstra(v1, v2);
		int v2ToN = dijkstra(v2, N);

		if(oneToV1 != -1 && v1ToV2 != -1 && v2ToN != -1) {
			route1 = oneToV1 + v1ToV2 + v2ToN;
		}

		int oneToV2 = dijkstra(1, v2);
		int v2ToV1 = v1ToV2; // 이미 위에서 구한 값
		int v1ToN = dijkstra(v1, N);

		if(oneToV2 != -1 && v2ToV1 != -1 && v1ToN != -1) {
			route2 = oneToV2 + v2ToV1 + v1ToN;
		}

		int answer;
		if(route1 == -1 && route2 == -1) answer = -1;
		else if(route1 == -1) answer = route2;
		else if(route2 == -1) answer = route1;
		else answer = Math.min(route1, route2);

		System.out.println(answer);
	}
	
	static void init() throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		E = Integer.parseInt(st.nextToken());
		map = new int[N + 1];
		roads = new List[N + 1];
		
		for(int i = 0 ; i <= N ; i++) {
			roads[i] = new ArrayList<>();
		}
		
		int a, b, c;
		for(int i = 0 ; i < E ; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			roads[a].add(new Road(b, c));
			roads[b].add(new Road(a, c));
		}
		
		st = new StringTokenizer(br.readLine());
		v1 = Integer.parseInt(st.nextToken());
		v2 = Integer.parseInt(st.nextToken());
		
		for(Road r : roads[v1]) {
			if(r.next == v2) {
				vLength = r.length;
				break;
			}
		}
	}// init()
	
	static int dijkstra(int start, int end) {
		Arrays.fill(map, Integer.MAX_VALUE);
		map[start] = 0;
		PriorityQueue<Road> pq = new PriorityQueue<>(Comparator.comparingInt(r -> r.length));
		pq.add(new Road(start, 0));
		
		while (!pq.isEmpty()) {
			Road current = pq.poll();
			int curr = current.next;
			int cost = current.length;
			
			if (map[curr] < cost) continue;
			
			for (Road r : roads[curr]) {
				int next = r.next;
				int newCost = cost + r.length;
				if (map[next] > newCost) {
					map[next] = newCost;
					pq.add(new Road(next, newCost));
				}
			}
		}
		
		return map[end] == Integer.MAX_VALUE ? -1 : map[end];
	}
}
