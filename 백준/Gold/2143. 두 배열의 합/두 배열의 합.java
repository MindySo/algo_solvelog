import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long T = Integer.parseInt(br.readLine().trim());
		
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
		Map<Long, Integer> mapA = new HashMap<>();
		for(int i = 0 ; i < N ; i++) {
			long inter = 0;
			for(int j = i ; j < N ; j++) {
				inter += A[j];
				mapA.put(inter, mapA.getOrDefault(inter, 0) + 1);
			}
		}
		
		Map<Long, Integer> mapB = new HashMap<>();
		for(int i = 0 ; i < M ; i++) {
			long inter = 0;
			for(int j = i ; j < M ; j++) {
				inter += B[j];
				mapB.put(inter, mapB.getOrDefault(inter, 0) + 1);
			}
		}
		
		BigInteger ans = BigInteger.ZERO;
		BigInteger valA, valB;
		for(long inter : mapA.keySet()) {
			if(mapB.containsKey(T - inter)) {
				valA = BigInteger.valueOf(mapA.get(inter));
				valB = BigInteger.valueOf(mapB.get(T - inter));
				ans = ans.add(valA.multiply(valB));
			}
		}
		
		System.out.println(ans);
	}
}
