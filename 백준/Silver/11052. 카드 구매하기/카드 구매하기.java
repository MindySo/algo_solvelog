import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] dp = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		dp[1] = Integer.parseInt(st.nextToken());
		if(N == 1) {
			System.out.println(dp[1]);
			return;
		}

		for (int i = 2; i <= N; i++) {
			dp[i] = Integer.parseInt(st.nextToken());
			for (int j = i / 2; j <= i; j++) {
				dp[i] = Math.max(dp[i], dp[j] * (i / j) + dp[i % j]);
			}
		}
		System.out.println(dp[N]);
	}
}