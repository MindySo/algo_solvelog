import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static boolean[] col, rightup, rightdown;
	static int ans;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine().trim());
		col = new boolean[N];
		rightup = new boolean[N * 2]; 
		rightdown = new boolean[N * 2]; 

		ans = 0;
		DFS(0, 0);
		System.out.println(ans);
	}


	static void DFS(int r, int count) {
		if (count == N) {
			ans++;
			return;
		}

		if (r >= N) {
			return;
		}

		for (int c = 0; c < N; c++) {
			if(col[c] || rightup[r + c] || rightdown[r - c + N]) {
				continue;
			}

			col[c] = rightup[r + c] = rightdown[r - c + N] = true;
			DFS(r + 1, count + 1);
			col[c] = rightup[r + c] = rightdown[r - c + N] = false;
		}
	}
}
