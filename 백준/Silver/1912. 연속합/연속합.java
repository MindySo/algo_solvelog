import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int max = Integer.MIN_VALUE;
		int sum = 0;
		int curr;
		for(int i = 1 ; i <= N ; i++) {
			curr = Integer.parseInt(st.nextToken());
			sum = Math.max(sum + curr, curr);
			max = Math.max(sum, max);
		}
		System.out.println(max);
	}
}
