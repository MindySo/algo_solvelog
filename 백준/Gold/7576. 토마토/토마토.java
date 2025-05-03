import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[][] arr = new int[N][M];
		Queue<int[]> que = new LinkedList<>();
		int green = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 0) {
					green++;
				} else if (arr[i][j] == 1) {
					que.add(new int[] { i, j, 1 });
				}
			}
		}
		if (green == 0) {
			System.out.println(0);
			return;
		}

		int[] dr = { -1, 0, 0, 1 };
		int[] dc = { 0, -1, 1, 0 };
		int[] temp;
		int r, c, day, newR, newC;
		while (!que.isEmpty()) {
			temp = que.poll();
			r = temp[0];
			c = temp[1];
			day = temp[2];
			for (int d = 0; d < 4; d++) {
				newR = r + dr[d];
				newC = c + dc[d];
				if (newR >= 0 && newR < N && newC >= 0 && newC < M &&
						arr[newR][newC] == 0) {
					green--;
					if (green == 0) {
						System.out.println(day);
						return;
					}
					arr[newR][newC] = 1;
					que.add(new int[] { newR, newC, day + 1 });
				}
			}
		}

		System.out.println(-1);
	}
}
