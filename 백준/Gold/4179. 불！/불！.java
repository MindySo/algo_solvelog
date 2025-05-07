import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int R = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		char[][] map = new char[R][C];
		int[][] fireTime = new int[R][C];
		boolean[][] visited = new boolean[R][C];
		
		Queue<int[]> que = new LinkedList<>();
		int[] jihun = new int[3];
		String str;
		for (int i = 0; i < R; i++) {
			str = br.readLine();
			Arrays.fill(fireTime[i], Integer.MAX_VALUE);
			for (int j = 0; j < C; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'F') {
					que.add(new int[] { i, j, 0 });
				}else if(map[i][j] == '#') {
					fireTime[i][j] = -1;
				}else if(map[i][j] == 'J') {
					jihun[0] = i;
					jihun[1] = j;
				}
			}
		}

		int[] dr = {-1, 0, 0, 1};
		int[] dc = {0, -1, 1, 0};
		int[] temp;
		int r, c, time, newR, newC;
		while (!que.isEmpty()) {
			temp = que.poll();
			r = temp[0];
			c = temp[1];
			time = temp[2];
			if(fireTime[r][c] == Integer.MAX_VALUE) {
				fireTime[r][c] = time;
			}else {
				continue;
			}
			
			for(int d = 0 ; d < 4 ; d++) {
				newR = r + dr[d];
				newC = c + dc[d];
				if(newR >= 0 && newR < R && newC >= 0 && newC < C && fireTime[newR][newC] == Integer.MAX_VALUE) {
					que.add(new int[] {newR, newC, time + 1});
				}
			}
		}
		
		String ans = "IMPOSSIBLE";
		que.add(jihun);
		while(!que.isEmpty()) {
			temp = que.poll();
			r = temp[0];
			c = temp[1];
			time = temp[2];
			
			if(visited[r][c]) {
				continue;
			}
			
			if(r == 0 || c == 0 || r == R-1 || c == C-1) {
				ans = (time + 1) + "";
				break;
			}
			
			visited[r][c] = true;
			
			for(int d = 0 ; d < 4 ; d++) {
				newR = r + dr[d];
				newC = c + dc[d];
				if(newR >= 0 && newR < R && newC >= 0 && newC < C && !visited[newR][newC] && fireTime[newR][newC] > time + 1) {
					que.add(new int[] {newR, newC, time + 1});
				}
			}
		}
		
		System.out.println(ans);
		
	}
}
