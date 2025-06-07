import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int[][] wheel;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		wheel = new int[5][8];
		String input;
		for (int i = 1; i <= 4; i++) {
			input = br.readLine();
			for (int j = 0; j < 8; j++) {
				wheel[i][j] = input.charAt(j) - '0';
			}
		}

		int K = Integer.parseInt(br.readLine().trim());

		int num, dir, currNum, currDir;
		boolean between[] = new boolean[4];
		for (int rt = 0; rt < K; rt++) {
			st = new StringTokenizer(br.readLine());
			num = Integer.parseInt(st.nextToken());
			dir = Integer.parseInt(st.nextToken());

			// 톱니바퀴 맞닿는 부분 극이 서로 다르면 true (1: 1-2 사이, 2: 2-3 사이, 3: 3-4 사이)
			for (int i = 1; i < 4; i++) {
				between[i] = (wheel[i][2] != wheel[i + 1][6]);
			}

			// 시작 톱니바퀴 돌리기
			rotate(num, dir);

			// 왼쪽 톱니바퀴로 설정
			currNum = num - 1;
			currDir = dir * -1;

			// 왼쪽 진행 : 1번까지, 전 톱니바퀴가 회전했을 때 연쇄
			while (currNum >= 1) {
				// between이 true면 돈다.
				if (between[currNum]) {
					rotate(currNum, currDir);
				} else {
					// 안 돌 경우 더 이상 진행하지 않아도 된다.
					break;
				}

				// 왼쪽 톱니바퀴로, 방향은 반대로
				currNum--;
				currDir *= -1;
			}

			// 오른쪽 톱니바퀴로 설정
			currNum = num + 1;
			currDir = dir * -1;

			// 오른쪽 진행 : 4번까지
			while (currNum <= 4) {
				// between이 true면 돈다.
				if (between[currNum - 1]) {
					rotate(currNum, currDir);
				} else {
					// 안 돌 경우 더 이상 진행하지 않아도 된다.
					break;
				}

				// 오른쪽 톱니바퀴로, 방향은 반대로
				currNum++;
				currDir *= -1;
			}
		}

		int ans = 0;
		for (int i = 0; i < 4; i++) {
			ans += (wheel[i + 1][0] * Math.pow(2, i));
		}

		System.out.println(ans);

	} // main

	static void rotate(int num, int dir) {
		if (dir == 1) {
			// 시계방향으로 숫자 밀기
			int last = wheel[num][7];
			for (int i = 7; i > 0; i--) {
				wheel[num][i] = wheel[num][i - 1];
			}
			wheel[num][0] = last;
		} else {
			// 반시계방향으로 숫자 밀기
			int first = wheel[num][0];
			for (int i = 0; i < 7; i++) {
				wheel[num][i] = wheel[num][i + 1];
			}
			wheel[num][7] = first;
		}
	}

}
