import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static long[] arr;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		arr = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(arr);

		int idx, a = 0, b = 0, c = 0;
		Long dist, closest = Long.MAX_VALUE;
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N - 1; j++) {
				dist = arr[i] + arr[j];
				idx = binary(j + 1, dist);
				
				dist = Math.abs(dist + arr[idx]);
				if(dist < closest){
					closest = dist;
					a = i;
					b = j;
					c = idx;
				}
			}
		}
		
		System.out.println(arr[a] + " " + arr[b] + " " + arr[c]);
	}// main()

	static int binary(int start, long mix) {
		long find = -mix;
		int left = start;
		int right = N - 1;
		int mid = (left + right) / 2;
		
		int idx = 0;
		long dist = Long.MAX_VALUE;
		
		while (left <= right) {
			mid = (left + right) / 2;
			long absVal = Math.abs(mix + arr[mid]);
			
			if(absVal < dist) {
				dist = absVal;
				idx = mid;
			}

			if (arr[mid] < find) {
				left = mid + 1;
			} else if (arr[mid] > find) {
				right = mid - 1;
			} else {
				break;
			}
		}
		return idx;
	}
}