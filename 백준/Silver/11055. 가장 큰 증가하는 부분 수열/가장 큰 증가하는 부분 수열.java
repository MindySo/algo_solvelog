import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] arr = new int[N + 1];
		int[] dp = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		int max = arr[1] = dp[1] = Integer.parseInt(st.nextToken());

		for (int i = 2; i <= N; i++) {
			arr[i] = dp[i] = Integer.parseInt(st.nextToken());
			for (int j = 1; j <= i; j++) {
				if (arr[j] < arr[i]) {
					dp[i] = Math.max(dp[i], dp[j] + arr[i]);
				}
			}
			max = Math.max(max, dp[i]);
		}
		System.out.println(max);
	}
}