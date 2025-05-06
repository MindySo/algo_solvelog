import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		boolean[][][] visited = new boolean[N][M][2]; // [행][열][벽 부숨 여부]
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}

		int ans = -1;
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] { 0, 0, 1, 0 });

		int[] dr = { -1, 0, 0, 1 };
		int[] dc = { 0, -1, 1, 0 };
		int[] temp;
		int r, c, dist, broke, newR, newC;
		while (!que.isEmpty()) {
			temp = que.poll();
			r = temp[0];
			c = temp[1];
			dist = temp[2];
			broke = temp[3];
			
			if (r == N - 1 && c == M - 1) {
				ans = dist;
				break;
			}

			for (int d = 0; d < 4; d++) {
				newR = r + dr[d];
				newC = c + dc[d];
				if (newR >= 0 && newR < N && newC >= 0 && newC < M) {
					if(map[newR][newC] == 0 && !visited[newR][newC][broke]) {
						visited[newR][newC][broke] = true;
						que.add(new int[] { newR, newC, dist + 1, broke });
					}else if(map[newR][newC] == 1 && broke == 0 && !visited[newR][newC][broke]) {
						visited[newR][newC][1] = true;
						que.add(new int[] { newR, newC, dist + 1, 1 });
					}
				}
			}
		}
		System.out.println(ans);
	}
}
