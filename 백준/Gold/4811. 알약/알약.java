import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static long[][] dp;

    public static long dfs(int whole, int half) {
        if (whole == 0 && half == 0) return 1;
        if (dp[whole][half] != -1) return dp[whole][half];

        long count = 0;

        if (whole > 0) count += dfs(whole - 1, half + 1); // W 선택
        if (half > 0) count += dfs(whole, half - 1);      // H 선택

        return dp[whole][half] = count;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        while (!(line = br.readLine()).equals("0")) {
            int N = Integer.parseInt(line);
            dp = new long[N + 1][N + 1];
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= N; j++) {
                    dp[i][j] = -1;
                }
            }

            System.out.println(dfs(N, 0));
        }
    }
}
