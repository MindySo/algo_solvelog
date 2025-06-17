import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine().trim());
		for(int tc = 1 ; tc <= T ; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			int[][] sticker = new int[2][N + 1];
			for(int i = 0 ; i < 2 ; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j = 1 ; j <= N ; j++) {
					sticker[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			int[][] dp = new int[2][N + 1];	// 해당 스티커를 붙일 때 최대
			dp[0][1] = sticker[0][1];
			dp[1][1] = sticker[1][1];
			if(N == 1) {
				sb.append(Math.max(dp[0][1], dp[1][1])).append("\n");
				continue;
			}
			
			dp[0][2] = sticker[1][1] + sticker[0][2];
			dp[1][2] = sticker[0][1] + sticker[1][2];
			
			for(int i = 3 ; i <= N ; i++) {
				dp[0][i] = Math.max(dp[1][i-2], dp[1][i-1]) + sticker[0][i];
				dp[1][i] = Math.max(dp[0][i-2], dp[0][i-1]) + sticker[1][i];
			}
			
			int ans = Math.max(dp[0][N], dp[1][N]);
			sb.append(ans).append("\n");
		}
		
		System.out.println(sb);
	}
}