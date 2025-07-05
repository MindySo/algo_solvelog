import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int[] map = new int[W];
		for(int i = 0 ; i < W ; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}
		
		int prev, curr, cnt, ans = 0;
		boolean started;
		
		for(int h = 1 ; h <= H ; h++) {
			started = false;
			prev = -1;
			cnt = 0;
			for(int i = 0 ; i < W ; i++) {
				curr = map[i] - h;
				if(!started && prev >= 0 && curr < 0) {
					started = true;
					cnt++;
				}else if(started && curr < 0) {
					cnt++;
				}else if(started && curr >= 0) {
					ans += cnt;
					cnt = 0;
					started = false;
				}
				prev = curr;
			}
		}
		System.out.println(ans);
	}// main();
}
