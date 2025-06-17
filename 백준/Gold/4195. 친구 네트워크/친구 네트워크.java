import java.io.*;
import java.util.*;

public class Main {
	static Map<String, Integer> personNo;
	static int[] union;
	static int[] friendCnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine().trim());
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			personNo = new HashMap<>();
			union = new int[N * 2 + 1];
			friendCnt = new int[N * 2 + 1];

			int idx = 0;
			int noA, noB, unionA, unionB, unionMain;
			String personA, personB;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				personA = st.nextToken();
				personB = st.nextToken();
				
				if(!personNo.containsKey(personA)) {
					personNo.put(personA, ++idx);
					union[idx] = idx;
					friendCnt[idx] = 1;
				}
				
				if(!personNo.containsKey(personB)) {
					personNo.put(personB, ++idx);
					union[idx] = idx;
					friendCnt[idx] = 1;
				}
				
				noA = personNo.get(personA);
				noB = personNo.get(personB);
				unionA = findUnion(noA);
				unionB = findUnion(noB);
				
				if(unionA > unionB){
					unionMain = unionB;
					friendCnt[unionMain] += friendCnt[unionA];
					union[unionA] = unionMain;
				}else if(unionA < unionB){
					unionMain = unionA;
					friendCnt[unionMain] += friendCnt[unionB];
					union[unionB] = unionMain;
				}else {
					unionMain = unionA;
				}
				
				sb.append(friendCnt[unionMain]).append("\n");
			}
		}	// for(tc)
		
		System.out.println(sb);
	}
	
	static int findUnion(int no) {
		if(union[no] == no) {
			return no;
		}
		
		return union[no] = findUnion(union[no]);
	}
}
