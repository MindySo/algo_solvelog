import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[][] map = new int[N][N];

		String str;
		int val;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < N; j++) {
				val = str.charAt(j) - '0';
				map[i][j] = val == 0 ? 1 : 0;
			}
		}

		int[] dr = { -1, 0, 0, 1 };
		int[] dc = { 0, -1, 1, 0 };

		int[][] dij = new int[N][N];
		for(int i = 0 ; i < N ; i++) {
			Arrays.fill(dij[i], Integer.MAX_VALUE);
		}

		PriorityQueue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
		});

		dij[0][0] = 0;
		que.add(new int[] {0, 0});
		
		int[] curr;
		int r, c, newR, newC;
		while (!que.isEmpty()) {
			curr = que.poll();
			r = curr[0];
			c = curr[1];
			
			for(int d = 0 ; d < 4 ; d++) {
				newR = r + dr[d];
				newC = c + dc[d];
				if(newR >= 0 && newR < N && newC >= 0 && newC < N) {
					if(dij[r][c] + map[newR][newC] < dij[newR][newC]) {
						dij[newR][newC] = dij[r][c] + map[newR][newC];
						que.add(new int[] {newR, newC});
					}
				}
			}
		}
			
		System.out.println(dij[N-1][N-1]);
	}
}
