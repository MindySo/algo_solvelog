import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		final int MOD = 1_000_000_000;

		int[][] dp = new int[N + 1][10];
		for (int i = 1; i <= 9; i++) {
			dp[1][i] = 1;
		}

		for (int i = 2; i <= N; i++) {
			dp[i][0] = dp[i - 1][1];
			dp[i][9] = dp[i - 1][8];
			for (int n = 1; n <= 8; n++) {
				dp[i][n] = (dp[i - 1][n - 1] + dp[i - 1][n + 1]) % MOD;
			}
		}
		
		long ans = 0;
		for(int i = 0 ; i <= 9 ; i++) {
			ans += dp[N][i];
			ans %= MOD;
		}
		
		System.out.println(ans);
	}
}
