import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int H = Integer.parseInt(st.nextToken());

		Queue<int[]> que = new LinkedList<>();
		int[][][] box = new int[H][N][M];
		int green = 0;
		for (int h = 0; h < H; h++) {
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				for (int m = 0; m < M; m++) {
					box[h][n][m] = Integer.parseInt(st.nextToken());
					if (box[h][n][m] == 1) {
						que.add(new int[] { h, n, m, 0 });
					} else if (box[h][n][m] == 0) {
						green++;
					}
				}
			}
		}

		if (green == 0) {
			System.out.println(0);
			return;
		}

		int[] dr = { -1, 0, 0, 1, 0, 0 };
		int[] dc = { 0, -1, 1, 0, 0, 0 };
		int[] dh = { 0, 0, 0, 0, -1, 1 };

		int ans = 0;
		int[] temp;
		int h, n, m, day, newH, newN, newM;
		while (!que.isEmpty()) {
			temp = que.poll();
			h = temp[0];
			n = temp[1];
			m = temp[2];
			day = temp[3];

			if (ans < day) {
				ans = day;
			}

			for (int d = 0; d < 6; d++) {
				newN = n + dr[d];
				newM = m + dc[d];
				newH = h + dh[d];
				if (newN >= 0 && newN < N && newM >= 0 && newM < M && 
						newH >= 0 && newH < H && box[newH][newN][newM] == 0) {
					que.add(new int[] { newH, newN, newM, day + 1 });
					box[newH][newN][newM] = 1;
					green--;
				}
			}
		}

		if (green > 0) {
			System.out.println(-1);
		} else {
			System.out.println(ans);
		}

	}
}
