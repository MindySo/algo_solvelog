import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int k = 0;
		while (Math.pow(2, k) < N) {
			k++;
		}
		int treeSize = (int) Math.pow(2, k + 1) - 1;
		int leafStart = (int) Math.pow(2, k) - 1;

		int[] maxTree = new int[treeSize];
		int[] minTree = new int[treeSize];

		int curr;
		for (int i = 0; i < N; i++) {
			curr = Integer.parseInt(br.readLine());
			maxTree[leafStart + i] = curr;
			minTree[leafStart + i] = curr;
		}
		for (int i = leafStart + N; i < minTree.length; i++) {
			minTree[i] = Integer.MAX_VALUE;
		}
		for (int i = 0; i < leafStart; i++) {
			maxTree[i] = 0;
			minTree[i] = Integer.MAX_VALUE;
		}
		int parent;
		for (int i = treeSize - 1; i > 0; i--) {
			parent = (i - 1) / 2;
			minTree[parent] = Math.min(minTree[parent], minTree[i]);
			maxTree[parent] = Math.max(maxTree[parent], maxTree[i]);
		}

		StringBuilder sb = new StringBuilder();
		for (int m = 0; m < M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()) - 1 + leafStart;
			int b = Integer.parseInt(st.nextToken()) - 1 + leafStart;
			int min = Integer.MAX_VALUE;
			int max = 0;

			while (a <= b) {
				if (a == b) {
					min = Math.min(min, minTree[a]);
					max = Math.max(max, maxTree[a]);
					break;
				}

				if (a % 2 == 0) {
					min = Math.min(min, minTree[a]);
					max = Math.max(max, maxTree[a]);
					a++;
				}
				if (b % 2 == 1) {
					min = Math.min(min, minTree[b]);
					max = Math.max(max, maxTree[b]);
					b--;
				}

				a = (a - 1) / 2;
				b = (b - 1) / 2;
			}
			sb.append(min).append(" ").append(max).append("\n");
		}
		System.out.println(sb);
	}
}