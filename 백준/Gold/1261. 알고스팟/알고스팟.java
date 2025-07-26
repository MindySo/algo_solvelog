import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		String str;
		for(int i = 0 ; i < N ; i++) {
			str = br.readLine();
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = str.charAt(j) - '0';
			}
		}
		
		int[] dr = {-1, 0, 0, 1};
		int[] dc = {0, -1, 1, 0};
		int[][] dij = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			Arrays.fill(dij[i], Integer.MAX_VALUE);
		}
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {0, 0, 0});
		int[] temp;
		int r, c, cost, newR, newC;
		while(!que.isEmpty()) {
			temp = que.poll();
			r = temp[0];
			c = temp[1];
			cost = temp[2];
			
			if(cost >= dij[r][c]) {
				continue;
			}
			dij[r][c] = cost;
			for(int d = 0 ; d < 4 ; d++) {
				newR = r + dr[d];
				newC = c + dc[d];
				if(newR >= 0 && newR < N && newC >= 0 && newC < M) {
					que.add(new int[] {newR, newC, cost + map[newR][newC]});
				}
			}
		}
		
		System.out.println(dij[N-1][M-1]);
	}
}
