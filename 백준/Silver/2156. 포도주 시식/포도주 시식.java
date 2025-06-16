import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] arr = new int[N + 1];
		int[] dp = new int[N + 1];
		
		arr[1] = dp[1] = Integer.parseInt(br.readLine().trim());
		if(N == 1) {
			System.out.println(dp[1]);
			return;
		}
		
		arr[2] = Integer.parseInt(br.readLine().trim());
		dp[2] = arr[1] + arr[2];
		
		int twoThree, oneThree, oneTwo;
		for(int i = 3 ; i <= N ; i++) {
			arr[i] = Integer.parseInt(br.readLine());
			
			twoThree = dp[i-3] + arr[i-1] + arr[i];
			oneThree = dp[i-2] + arr[i];
			oneTwo = dp[i-1];
			dp[i] = Math.max(Math.max(twoThree, oneThree), oneTwo);
		}
		System.out.println(dp[N]);
	}
}
