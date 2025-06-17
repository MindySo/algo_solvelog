import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int N = Integer.parseInt(br.readLine().trim());
		
		int[][] dp = new int[N + 1][3];
		int curr;
		for(int i = 1 ; i <= N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 3 ; j++) {
				curr = Integer.parseInt(st.nextToken());
				dp[i][j] = Math.min(dp[i - 1][(j + 1) % 3], dp[i - 1][(j + 2) % 3]) + curr;
			}
		}
		
		int ans = Math.min(dp[N][0], Math.min(dp[N][1], dp[N][2]));
		System.out.println(ans);
	}
}
