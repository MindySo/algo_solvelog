import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		testCase:
		for(int tc = 0 ; tc < T ; tc++) {
			String p = br.readLine().trim();
			int n = Integer.parseInt(br.readLine().trim());
			String str = br.readLine().trim();
			str = str.substring(1, str.length() - 1);
			String[] arrStr = str.split(",");
			
			int[] arr = new int[n];
			for(int i = 0 ; i < n ; i++) {
				arr[i] = Integer.parseInt(arrStr[i]);
			}
			
			int length = n;
			int start = 0;
			int end = n-1;
			boolean dir = true;
			char input;
			for(int i = 0 ; i < p.length() ; i++) {
				input = p.charAt(i);
				if(input == 'R') {
					dir = !dir;
				}else if(input == 'D') {
					if(length == 0) {
						System.out.println("error");
						continue testCase;
					}
					
					if(dir) {
						arr[start++] = -1;
					}else {
						arr[end--] = -1;
					}
					length--;
				}
			}

			StringBuilder sb = new StringBuilder();
			if(length == 0) {
				sb.append("[]");
			}else {
				sb.append("[");
				if(dir) {
					for(int i = 0 ; i < n ; i++) {
						if(arr[i] == -1) {
							continue;
						}
						sb.append(arr[i]).append(",");
					}
				}else {
					for(int i = n - 1 ; i >= 0 ; i--) {
						if(arr[i] == -1) {
							continue;
						}
						sb.append(arr[i]).append(",");
					}
				}
				sb.deleteCharAt(sb.length() - 1);
				sb.append("]");
			}

			System.out.println(sb);
		}
	}
}
