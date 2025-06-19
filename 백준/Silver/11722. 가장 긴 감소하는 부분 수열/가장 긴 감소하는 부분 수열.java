import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] arr = new int[N + 1];
		int[] dp = new int[N + 1];

		StringTokenizer st = new StringTokenizer(br.readLine());
		arr[1] = Integer.parseInt(st.nextToken());
		int max = dp[1] = 1;
		
		for(int i = 2 ; i <= N ; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
			for(int j = 0 ; j < i ; j++) {
				if(arr[j] > arr[i] && dp[j] + 1 > dp[i]) {
					dp[i] = dp[j] + 1;
				}
			}
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
		
	}// main()
}