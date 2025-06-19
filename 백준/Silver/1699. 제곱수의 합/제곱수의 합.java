import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] dp = new int[N + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		int maxRoot, cnt;
		for(int i = 1 ; i <= N ; i++) {
			maxRoot = (int)Math.sqrt(i);
			for(int j = 1 ; j <= maxRoot ; j++) {
				cnt = i / (j * j) + dp[i % (j * j)];
				dp[i] = Math.min(dp[i], cnt);
			}
		}
		System.out.println(dp[N]);
		
	}// main()
}