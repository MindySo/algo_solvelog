import java.io.*;
import java.util.*;

public class Main {
	static class Control {
		int from;
		int to;
		int cost;

		Control(int from, int to, int cost) {
			this.from = from;
			this.to = to;
			this.cost = cost;
		}
	}

	static class NumAndCost {
		long arrNum;
		int totalCost;

		NumAndCost(long arrNum, int totalCost) {
			this.arrNum = arrNum;
			this.totalCost = totalCost;
		}
	}

	static int N, M, minCost;
	static int[] arr;
	static List<Control> ctrl;
	static Map<Long, Integer> visited;
	static Long sortedNum;

	public static void main(String[] args) throws IOException {
		getInput();

		visited = new HashMap<>();
		dijkstra(new NumAndCost(getNum(), 0));

		System.out.println(visited.getOrDefault(sortedNum, -1));

	}// main()

	static void getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		M = Integer.parseInt(br.readLine());
		ctrl = new ArrayList<Control>();
		int to, from, cost;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			to = Integer.parseInt(st.nextToken());
			from = Integer.parseInt(st.nextToken());
			cost = Integer.parseInt(st.nextToken());
			ctrl.add(new Control(from, to, cost));
		}

		int[] copy = Arrays.copyOf(arr, N + 1);
		Arrays.sort(arr);
		sortedNum = getNum();
		arr = copy;
	}// getInput()

	static long getNum() {
		long num = 0;
		for (int i = 1; i <= N; i++) {
			if (arr[i] != 10) {
				num = (num + (long) arr[i]) * 10;
			} else {
				num = (num + 1) * 100;
			}
		}
		return num / 10;
	}// getNum

	static void getArr(long arrNum) {
		for (int i = N; i >= 1; i--) {
			arr[i] = (int) (arrNum % 10);
			arrNum /= 10;

			if (arr[i] == 0) {
				arr[i] = 10;
				arrNum /= 10;
			}
		}
	}

	static void DFS(long arrNum, int totalCost) {
		int minCostOfArrNum = visited.getOrDefault(arrNum, Integer.MAX_VALUE);

		if (totalCost >= minCostOfArrNum) {
			return;
		}

		visited.put(arrNum, totalCost);

		if (arrNum == sortedNum) {
			return;
		}

		int temp;
		long newArrNum;
		for (Control control : ctrl) {
			getArr(arrNum);

			temp = arr[control.to];
			arr[control.to] = arr[control.from];
			arr[control.from] = temp;

			newArrNum = getNum();
			DFS(newArrNum, totalCost + control.cost);
		}
	}

	static void dijkstra(NumAndCost numAndCost) {
		
		PriorityQueue<NumAndCost> pq = new PriorityQueue<>(new Comparator<NumAndCost>() {

			@Override
			public int compare(NumAndCost o1, NumAndCost o2) {
				return o1.totalCost - o2.totalCost;
			}
		});
		
		pq.add(numAndCost);
		
		NumAndCost nc;
		while(!pq.isEmpty()) {
			nc = pq.poll();

			int minCostOfArrNum = visited.getOrDefault(nc.arrNum, Integer.MAX_VALUE);
			
			if (nc.totalCost >= minCostOfArrNum) {
				continue;
			}
			
			visited.put(nc.arrNum, nc.totalCost);
			
			if (nc.arrNum == sortedNum) {
				continue;
			}
			
			int temp;
			long newArrNum;
			for (Control control : ctrl) {
				getArr(nc.arrNum);
				
				temp = arr[control.to];
				arr[control.to] = arr[control.from];
				arr[control.from] = temp;
				
				newArrNum = getNum();
				pq.add(new NumAndCost(newArrNum, nc.totalCost + control.cost));
			}
		}// while()
	}// dijkstra()
}