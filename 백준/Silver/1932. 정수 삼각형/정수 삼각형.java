import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
    	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    	int N = Integer.parseInt(br.readLine());
    	int[][] map = new int[N][N];
    	int[][] dij = new int[N][N];
    	
    	StringTokenizer st;
    	for(int i = 0 ; i < N ; i++) {
    		st = new StringTokenizer(br.readLine());
    		for(int j = 0 ; j <= i ; j++) {
    			map[i][j] = Integer.parseInt(st.nextToken());
    		}
    	}
    	if(N == 1) {
    		System.out.println(map[0][0]);
    		return;
    	}
    	
    	int val;
    	dij[0][0] = map[0][0];
    	for(int i = 0 ; i < N - 1 ; i++) {
    		for(int j = 0 ; j <= i ; j++) {
    			val = dij[i][j] + map[i + 1][j];
    			if(val > dij[i + 1][j]) {
    				dij[i + 1][j] = val;
    			}

    			val = dij[i][j] + map[i + 1][j + 1];
    			if(val > dij[i + 1][j + 1]) {
    				dij[i + 1][j + 1] = val;
    			}
    		}
    	}
    	
    	int ans = 0;
    	for(int i = 0 ; i < N ; i++) {
    		if(dij[N - 1][i] > ans) {
    			ans = dij[N - 1][i];
    		}
    	}
    	System.out.println(ans);
    }
}