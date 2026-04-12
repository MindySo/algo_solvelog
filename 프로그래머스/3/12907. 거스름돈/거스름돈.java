import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        int MOD = 1_000_000_007;

        for (int m : money) {
            for (int i = m; i <= n; i++) {
                dp[i] = (dp[i] + dp[i - m]) % MOD;
            }
        }

        return dp[n];
    }
}