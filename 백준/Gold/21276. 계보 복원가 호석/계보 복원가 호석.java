import java.io.*;
import java.util.*;

public class Main {
	static class Person{
		Set<String> ancs;
		Set<String> decs;
		Set<String> childs;
		
		Person() {
			this.ancs = new HashSet<>();
			this.decs = new HashSet<>();
			this.childs = new TreeSet<>();
		}
	}
	
	static int N, M;
	static Map<String, Person> people;
	static Set<String> ancestors;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		
		people = new TreeMap<>();
		ancestors = new TreeSet<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		String name1, name2;
		for(int i = 0 ; i < N ; i++) {
			name1 = st.nextToken();
			people.put(name1, new Person());
			ancestors.add(name1);
		}
		
		int M = Integer.parseInt(br.readLine().trim());
		for(int i = 0 ; i < M ; i++) {
			st = new StringTokenizer(br.readLine());
			name1 = st.nextToken();
			name2 = st.nextToken();
			
			people.get(name1).ancs.add(name2);
			people.get(name2).decs.add(name1);
			
			ancestors.remove(name1);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append(ancestors.size()).append("\n");
		
		Queue<String> que = new LinkedList<>();
		for(String anc : ancestors) {
			que.add(anc);
			sb.append(anc).append(" ");
		}
		sb.append("\n");
		
		String anc;
		while(!que.isEmpty()) {
			anc = que.poll();
			
			for(String dec : people.get(anc).decs) {
				people.get(dec).ancs.remove(anc);
				if(people.get(dec).ancs.isEmpty()) {
					people.get(anc).childs.add(dec);
					que.add(dec);
				}
			}
		}
		
		for(String person : people.keySet()) {
			sb.append(person).append(" ");
			sb.append(people.get(person).childs.size()).append(" ");
			for(String child : people.get(person).childs) {
				sb.append(child).append(" ");
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}// main()
}