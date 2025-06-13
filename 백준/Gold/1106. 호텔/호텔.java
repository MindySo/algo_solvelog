import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int C = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());


		int promote[] = new int[101];	// i 비용으로 늘릴 수있는 최대 고객 수 : 비용은 최대 100
		int cost, output;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cost = Integer.parseInt(st.nextToken());
			output = Integer.parseInt(st.nextToken());
			if (promote[cost] < output) {
				promote[cost] = output;
			}
		}

		int[] dp = new int[C * 100 + 1];	// i 비용에서 늘릴 수 있는 최대 고객, 비용은 최대 C * 100 + 1
		Arrays.fill(dp, 0);
		dp[0] = 0;
		
		for (int i = 1; i < dp.length; i++) {	// dp[i]가 C명 이상이면 중단하고 i 출력
			for (int j = 0; j <= 100 && j <= i; j++) {
				
				// promote에 저장된 값 없으면 continue
				if(promote[j] == 0) {
					continue;
				}
				
				if(dp[i] < dp[i - j] + promote[j]) {
					dp[i] = dp[i - j] + promote[j];
				}
				
				// dp[i]가 C명 이상이면 중단하고 i 출력
				if(dp[i] >= C) {
					System.out.println(i);
					return;
				}
			}
		}
	}
}
