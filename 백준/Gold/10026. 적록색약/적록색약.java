import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		
		char[][] grid = new char[N][N];
		String str;
		for(int i = 0 ; i < N ; i++) {
			str = br.readLine();
			for(int j = 0 ; j < N ; j++) {
				grid[i][j] = str.charAt(j);
			}
		}

		boolean[][] visited = new boolean[N][N];
		boolean[][] rgVisited = new boolean[N][N];
		Queue<Integer> que = new LinkedList<>();
		Queue<Integer> rgQue = new LinkedList<>();
		int cnt = 0;
		int rgCnt = 0;
		
		int[] dr = {-1, 0, 0, 1};
		int[] dc = {0, -1, 1, 0};
		int pos, r, c, newR, newC;
		char color;
		for(int i = 0 ; i < N ; i++) {
			for(int j = 0 ; j < N ; j++) {
				// 색약아님
				if(!visited[i][j]) {
					que.add(i * N + j);
					visited[i][j] = true;
					cnt++;
					while(!que.isEmpty()) {
						pos = que.poll();
						r = pos / N;
						c = pos % N;
						color = grid[r][c];
						for(int d = 0 ; d < 4 ; d++) {
							newR = r + dr[d];
							newC = c + dc[d];
							if(newR >= 0 && newR < N && newC >= 0 && newC < N && !visited[newR][newC] 
									&& color == grid[newR][newC]) {
								que.add(newR * N + newC);
								visited[newR][newC] = true;
							}
						}
					}
				}
				
				// 색약
				if(!rgVisited[i][j]) {
					rgQue.add(i * N + j);
					rgVisited[i][j] = true;
					rgCnt++;
					while(!rgQue.isEmpty()) {
						pos = rgQue.poll();
						r = pos / N;
						c = pos % N;
						color = grid[r][c];
						for(int d = 0 ; d < 4 ; d++) {
							newR = r + dr[d];
							newC = c + dc[d];
							if(newR >= 0 && newR < N && newC >= 0 && newC < N && !rgVisited[newR][newC]) {
								if((color != 'B' && grid[newR][newC] != 'B') 
										|| (color == 'B' && grid[newR][newC] == 'B')) {
									rgQue.add(newR * N + newC);
									rgVisited[newR][newC] = true;
								}
							}
						}
					}
				}
			}
		}// for
		
		System.out.println(cnt + " " + rgCnt);
	}
}
