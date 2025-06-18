import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		final int MOD = 10007;
		
		int[][] dp = new int[N + 1][10];
		Arrays.fill(dp[1], 1);

		for (int i = 2; i <= N; i++) {
			for (int j = 0; j <= 9; j++) {
				for(int n = 9 ; n >= j ; n--) {
					dp[i][j] = (dp[i][j] + dp[i-1][n]) % MOD;
				}
			}
		}

		int ans = 0;
		for (int i = 0; i <= 9; i++) {
			ans = (ans + dp[N][i]) % MOD;
		}

		System.out.println(ans);
	}
}