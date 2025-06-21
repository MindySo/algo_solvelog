import java.io.*;
import java.util.*;

public class Main {
	static int N, M;
	static int redR, redC, blueR, blueC;
	static char[][] map;

	public static void main(String[] args) throws IOException {
		getInput();
		int result = BFS();
		System.out.println(result);
		
	}// main()

	static void getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][M];
		String str;
		for (int i = 0; i < N; i++) {
			str = br.readLine();
			for (int j = 0; j < M; j++) {
				map[i][j] = str.charAt(j);
				if (map[i][j] == 'R') {
					redR = i;
					redC = j;
					map[i][j] = '.';
				} else if (map[i][j] == 'B') {
					blueR = i;
					blueC = j;
					map[i][j] = '.';
				}
			}
		}
	}// getInput()

	static int BFS() {

		int[] dr = { -1, 0, 0, 1 };
		int[] dc = { 0, -1, 1, 0 };

		Queue<int[]> que = new LinkedList<>();
		Set<Integer> visited = new HashSet<>();
		que.add(new int[] { getNum(redR, redC, blueR, blueC), 0 });

		int[] temp;
		int beedsNum, depth, newRedR, newRedC, newBlueR, newBlueC, newBeedsNum;
		boolean redAhead, blueAhead;
		while (!que.isEmpty()) {
			temp = que.poll();
			beedsNum = temp[0];
			depth = temp[1];

			if (depth > 10) {
				return 0;
			}
			
			if (visited.contains(beedsNum)) {
				continue;
			}


			visited.add(beedsNum);
			setBeeds(beedsNum);

			if (map[blueR][blueC] == 'O') {
				continue;
			} else if (map[redR][redC] == 'O') {
				return 1;
			}

			for (int d = 0; d < 4; d++) {
				newRedR = redR;
				newRedC = redC;
				newBlueR = blueR;
				newBlueC = blueC;

				redAhead = false;
				blueAhead = false;

				while (map[newRedR + dr[d]][newRedC + dc[d]] != '#') {
					newRedR += dr[d];
					newRedC += dc[d];

					if (newRedR == blueR && newRedC == blueC) {
						blueAhead = true;
					}

					if (map[newRedR][newRedC] == 'O') {
						break;
					}
				}

				while (map[newBlueR + dr[d]][newBlueC + dc[d]] != '#') {
					newBlueR += dr[d];

					newBlueC += dc[d];
					if (newBlueR == redR && newBlueC == redC) {
						redAhead = true;
					}
					if (map[newBlueR][newBlueC] == 'O') {
						break;
					}
				}

				if (redAhead && map[newRedR][newRedC] != 'O') {
					newBlueR -= dr[d];
					newBlueC -= dc[d];
				}else if (blueAhead && map[newBlueR][newBlueC] != 'O') {
					newRedR -= dr[d];
					newRedC -= dc[d];
				}	

				newBeedsNum = getNum(newRedR, newRedC, newBlueR, newBlueC);
				que.add(new int[] { newBeedsNum, depth + 1 });
			}// for(d)
		}// while()

		return 0;
	}// BFS()

	static int getNum(int redR, int redC, int blueR, int blueC) {
		return (((((redR * 10) + redC) * 10) + blueR) * 10) + blueC;
	}// getNum()

	static void setBeeds(int beedsNum) {
		blueC = beedsNum % 10;
		beedsNum /= 10;
		blueR = beedsNum % 10;
		beedsNum /= 10;
		redC = beedsNum % 10;
		beedsNum /= 10;
		redR = beedsNum;
	}// setBeeds()
}