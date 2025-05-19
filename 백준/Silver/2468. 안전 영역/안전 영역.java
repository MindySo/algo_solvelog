import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[][] map = new int[N][N];
		int maxTide = 0;
		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] > maxTide) {
					maxTide = map[i][j];
				}
			}
		}

		int[] dr = { -1, 0, 0, 1 };
		int[] dc = { 0, -1, 1, 0 };
		int temp, r, c, newR, newC;
		int cnt;
		int max = 1;

		Queue<Integer> que = new LinkedList<>();
		for (int tide = 1; tide < maxTide; tide++) {
			boolean[][] visited = new boolean[N][N];
			cnt = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(!visited[i][j] && map[i][j] > tide) {
						visited[i][j] = true;
						que.add(i * N + j);
						cnt++;
					}
					
					while (!que.isEmpty()) {
						temp = que.poll();
						r = temp / N;
						c = temp % N;
						for(int d = 0 ; d < 4 ; d++) {
							newR = r + dr[d];
							newC = c + dc[d];
							if(newR >= 0 && newR < N && newC >= 0 && newC < N 
									&& !visited[newR][newC] && map[newR][newC] > tide) {
								que.add(newR * N + newC);
								visited[newR][newC] = true;
							}
						}
					}// bfs
				}
			}// map 순회
			if(cnt > max) {
				max = cnt;
			}
		}
		System.out.println(max);
	}
}