import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] arr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		List<Integer> list = new ArrayList<>();
		int idx;
		for (int n : arr) {
			idx = Collections.binarySearch(list, n);
			if (idx < 0) {
				idx = -(idx + 1);
			}
			
			if (idx == list.size()) {
				list.add(n);
				
			} else {
				list.set(idx, n);
			}
		}

		System.out.println(list.size());

	} // main
}
