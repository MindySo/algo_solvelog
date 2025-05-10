import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int M;
	static int[][] map;
	static int ans;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		for(int i = 0 ; i < N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < M ; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		ans = 0;
		DFS(r, c, d);
		System.out.println(ans);
	}
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static void DFS(int r, int c, int d) {
		int newR, newC;
		if(map[r][c] == 0) {
			map[r][c] = 2;
			ans++;
		}
		
		int newD = d;
		for(int i = 0 ; i < 4 ; i++) {
			newD--;
			if(newD < 0) {
				newD += 4;
			}
			
			newR = r + dr[newD];
			newC = c + dc[newD];
			if(newR >= 0 && newR < N && newC >= 0 && newC < M && map[newR][newC] == 0) {
				DFS(newR, newC, newD);
				return;
			}
		}
		
		newD = (d + 2) % 4;
		newR = r + dr[newD];
		newC = c + dc[newD]; 
		if(newR >= 0 && newR < N && newC >= 0 && newC < M && map[newR][newC] != 1) {
			DFS(newR, newC, d);
			return;
		}
	}
	
}
