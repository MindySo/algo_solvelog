import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()) - 1;
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int pow = (int)Math.pow(2, N);
		int ans = 0;
		int[] temp;
		int div;
		do {
			temp = getDiv(pow, r, c);
			div = temp[0];
			r = temp[1];
			c = temp[2];
			
			ans += Math.pow(4, N--) * div;
			pow /= 2;
		}while(pow >= 2);
		
		ans += getDiv(1, r, c)[0];
		System.out.println(ans);
	}
	
	static int[] getDiv(int n, int r, int c) {
		int div = 0;
		if(r >= n) {
			div += 2;
			r -= n;
		}
		if(c >= n) {
			div += 1;
			c -= n;
		}
		return new int[] {div, r, c};
	}
}
