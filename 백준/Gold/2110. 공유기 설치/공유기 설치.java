import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		// 간격을 이분탐색
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		Long[] arr = new Long[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(br.readLine().trim());
		}
		Arrays.sort(arr);

		Long left = 1L;
		Long right = arr[N - 1];
		Long mid;
		
		Long ans = 0L;
		
		while(left <= right) {
			mid = (left + right) / 2;
			int last = 0;
			int curr = 1;
			int wifi = 1;

			while(curr < N) {
				if(arr[curr] - arr[last] >= mid) {
					last = curr;
					wifi++;
				}
				curr++;
			}
			if(wifi >= M) {
				left = mid + 1;
				ans = Math.max(ans, mid);
			}else {
				right = mid - 1;
			}
		}
		
		System.out.println(ans);
	}// main()
}