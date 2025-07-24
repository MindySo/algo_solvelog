import java.io.*;
import java.util.*;

public class Main {
	static class Jewl {
		int weight;
		int cost;
		Jewl(int weight, int cost){
			this.weight = weight;
			this.cost = cost;
		}
	}
	
	static int N, K;
	static List<Jewl> jewls;
	static TreeMap<Integer, Integer> bags;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		jewls = new ArrayList<>();
		bags = new TreeMap<>();
		
		int w, c;
		for(int i = 0 ; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			jewls.add(new Jewl(w, c));
		}
		
		jewls.sort(new Comparator<Jewl>() {
			@Override
			public int compare(Jewl o1, Jewl o2) {
				int val;
				if(o1.cost != o2.cost) {
					val = o2.cost - o1.cost;
				}else {
					val = o1.weight - o2.weight;
				}
				return val;
			}
		});
		
		int b;
		for(int i = 0 ; i< K ;i++) {
			b = Integer.parseInt(br.readLine().trim());
			bags.put(b, bags.getOrDefault(b, 0) + 1);
		}
		
		long ans = 0;
		
		for(Jewl jewl : jewls) {
			Integer minVal = bags.ceilingKey(jewl.weight);
			
			if(minVal != null) {
				int remain = bags.get(minVal) - 1;
				if(remain > 0) {
					bags.put(minVal, remain);
				}else {
					bags.remove(minVal);	
				}
				ans += jewl.cost;
			}
		}
		
		System.out.println(ans);
	}
}