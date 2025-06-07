import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int totalTime;
	static int[][] map;
	static int sharkR, sharkC, sharkSize, eatenFish;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 9) {
					sharkR = i;
					sharkC = j;
					map[i][j] = 0;
				}
			}
		}

		sharkSize = 2;
		totalTime = 0;
		eatenFish = 0;
		
		// 생선 위치 우선순위 큐 
		edible = new PriorityQueue<>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				int r1 = o1 / N;
				int c1 = o1 % N;

				int r2 = o2 / N;
				int c2 = o2 % N;

				if (r1 != r2) {
					return (o1 / N) - (o2 / N);
				} else {
					return c1 - c2;
				}
			}
		});

		boolean ate;

		// 먹을 수 있을 때까지 먹는다.
		do {
			ate = bfs();
		} while (ate);

		System.out.println(totalTime);

	} // main

	static int[] dr = { -1, 0, 0, 1 };
	static int[] dc = { 0, -1, 1, 0 };
	static boolean[][] visited;
	static int foundFishTime;
	static PriorityQueue<Integer> edible;

	static boolean bfs() {
		boolean ate = false;
		visited = new boolean[N][N];
		foundFishTime = Integer.MAX_VALUE;

		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { sharkR, sharkC, 0 });

		int[] temp;
		int r, c, time, newR, newC, newTime;

		ate: while (!que.isEmpty()) {
			temp = que.poll();
			r = temp[0];
			c = temp[1];
			time = temp[2];

			if (visited[r][c]) {
				continue;
			}

			visited[r][c] = true;

			for (int d = 0; d < 4; d++) {
				newR = r + dr[d];
				newC = c + dc[d];
				newTime = time + 1;
				if (newR >= 0 && newR < N && newC >= 0 && newC < N && !visited[newR][newC]) {
					// 빈칸이거나 사이즈가 같으면 지나감
					if (map[newR][newC] == 0 || sharkSize == map[newR][newC]) {
						que.add(new int[] { newR, newC, newTime });
						continue;
					}

					// 상어보다 크면 못 지나감
					if (sharkSize < map[newR][newC]) {
						continue;
					}

					// 상어보다 작으면 먹는데..
					if (sharkSize > map[newR][newC]) {
						if (newTime <= foundFishTime) { // 최단 거리인 생선이면 최단 거리 목록에 넣는다 (위치 우선순위 비교)
							foundFishTime = newTime;
							edible.add(newR * N + newC);
							continue;

						} else { // 최단 거리에서 벗어난 생선 발견 : 큐 종료해서 생선 먹으러 간다
							break ate;
						}
					}
				}
			}
		}

		// pq에 생선이 있으면 최우선순위를 꺼내서 먹는다
		if (!edible.isEmpty()) {
			eat();
			ate = true;
		}

		return ate;
	}

	static void eat() {
		int firstFish = edible.poll();
		edible.clear();
		int r = firstFish / N;
		int c = firstFish % N;

		map[r][c] = 0;
		eatenFish++;

		sharkR = r;
		sharkC = c;
		totalTime += foundFishTime;

		if (eatenFish == sharkSize) { // 먹은 물고기 수가 현재 사이즈와 같아지면 사이즈업
			sharkSize++;
			eatenFish = 0;
		}
	}

}
