import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long T = Long.parseLong(br.readLine().trim());
		
		int N = Integer.parseInt(br.readLine().trim());
		long[] A = new long[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < N ; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		int M = Integer.parseInt(br.readLine().trim());
		long[] B = new long[M];
		st = new StringTokenizer(br.readLine());
		for(int i = 0 ; i < M ; i++) {
			B[i] = Integer.parseInt(st.nextToken());
		}
		Map<Long, Long> mapA = new HashMap<>();
		for(int i = 0 ; i < N ; i++) {
			long inter = 0;
			for(int j = i ; j < N ; j++) {
				inter += A[j];
				mapA.put(inter, mapA.getOrDefault(inter, 0L) + 1);
			}
		}
		
		Map<Long, Long> mapB = new HashMap<>();
		for(int i = 0 ; i < M ; i++) {
			long inter = 0;
			for(int j = i ; j < M ; j++) {
				inter += B[j];
				mapB.put(inter, mapB.getOrDefault(inter, 0L) + 1);
			}
		}
		
		long ans = 0;
		for(long inter : mapA.keySet()) {
			if(mapB.containsKey(T - inter)) {
				ans += mapA.get(inter) * mapB.get(T-inter);
			}
		}
		
		System.out.println(ans);
	}
}
