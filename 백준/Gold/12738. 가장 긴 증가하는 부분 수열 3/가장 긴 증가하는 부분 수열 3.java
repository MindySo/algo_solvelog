import java.io.*;
import java.util.*;

public class Main {
	
	// 값이 list 마지막보다 크다 : add
	// 값이 list 마지막보다 작다 : list에서 현재 값보다 큰 첫번째 값을 교체

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		List<Long> list = new ArrayList<>();

		StringTokenizer st = new StringTokenizer(br.readLine());
		Long num = Long.parseLong(st.nextToken());
		list.add(num);

		newnum: for (int i = 1; i < N; i++) {
			num = Long.parseLong(st.nextToken());
			if (num > list.get(list.size() - 1)) {
				list.add(num);
			} else {
				int left = 0;
				int right = list.size() - 1;
				int mid = 0;
				int idx = 0;

				while (left <= right) {
					mid = (left + right) / 2;

					if (list.get(mid) < num) {
						left = mid + 1;
					} else {
						right = mid - 1;
						idx = mid;
					}
				}
				list.set(idx, num);
			}
		}

		System.out.println(list.size());
	}// main()
}