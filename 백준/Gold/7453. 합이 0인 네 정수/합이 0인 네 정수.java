import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int length;
	static int[] AB;
	static int[] CD;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		int[] A = new int[N];
		int[] B = new int[N];
		int[] C = new int[N];
		int[] D = new int[N];

		StringTokenizer st;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			A[i] = Integer.parseInt(st.nextToken());
			B[i] = Integer.parseInt(st.nextToken());
			C[i] = Integer.parseInt(st.nextToken());
			D[i] = Integer.parseInt(st.nextToken());
		}

		length = N * N;
		AB = new int[length];
		CD = new int[length];

		int idx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				AB[idx] = A[i] + B[j];
				CD[idx++] = C[i] + D[j];
			}
		}
		Arrays.sort(AB);
		Arrays.sort(CD);

		long ans = 0;
		int left, right, mid, zeroIdx, upperBound, lowerBound, val = 0;
		for (int i = 0; i < length; i++) {
			left = 0;
			right = length - 1;
			mid = (left + right) / 2;

			while (left <= right) {
				mid = (left + right) / 2;
				val = AB[i] + CD[mid];
				if (val < 0) {
					left = mid + 1;
				} else if (val > 0) {
					right = mid - 1;
				} else {
					break;
				}
			}
			if (val == 0) {
				ans += (getUpper(-AB[i]) - getLower(-AB[i]));
			}
		}

		System.out.println(ans);
	}// main()

	static int getUpper(int target) {
		int left = 0;
		int right = length;
		int mid = (left + right) / 2;

		while (left < right) {
			mid = (left + right) / 2;
			if (CD[mid] > target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return left;
	}
	
	static int getLower(int target) {
		int left = 0;
		int right = length;
		int mid = (left + right) / 2;
		
		while (left < right) {
			mid = (left + right) / 2;
			if (CD[mid] >= target) {
				right = mid;
			} else {
				left = mid + 1;
			}
		}
		
		return left;
	}
}