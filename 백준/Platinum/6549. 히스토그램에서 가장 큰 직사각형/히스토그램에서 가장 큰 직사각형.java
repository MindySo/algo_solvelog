import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			if (N == 0) {
				System.out.println(sb);
				return;
			}
			long[] arr = new long[N + 1];
			for (int i = 0; i < N; i++) {
				arr[i] = Long.parseLong(st.nextToken());
			}
			arr[N] = 0;

			Stack<Integer> stk = new Stack<>();
			int width = 0, start = 0, end = 0;
			long max = 0;

			while (end <= N) {
				while (!stk.isEmpty() && arr[end] < arr[stk.peek()]) {
					long height = arr[stk.pop()];
					if(stk.isEmpty()) {
						width = end;
					}else {
						width = end - (stk.peek() + 1);
					}
					max = Math.max(max, height * width);
				}
				stk.add(end++);
			}
			
			sb.append(max).append("\n");
		}
	}
}
