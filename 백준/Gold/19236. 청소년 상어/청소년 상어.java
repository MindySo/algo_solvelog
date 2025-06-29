import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int[][] fish;
	static int max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[4][4];
		fish = new int[17][3]; // [0]r [1]c [2]dir

		for (int i = 0; i < 4; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 4; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				fish[map[i][j]][0] = i;
				fish[map[i][j]][1] = j;
				fish[map[i][j]][2] = Integer.parseInt(st.nextToken());
			}
		}

		int ate = map[0][0];
		int sharkDir = fish[map[0][0]][2];
		fish[map[0][0]][2] = -1;

		max = ate;
		map[0][0] = 100;
		sharkDFS(0, 0, sharkDir, ate);

		System.out.println(max);
	}

	static int[] dr = { 10, -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dc = { 10, 0, -1, -1, -1, 0, 1, 1, 1 };

	static void moveFish(int f1) {
		int r = fish[f1][0];
		int c = fish[f1][1];
		int dir = fish[f1][2];

		if (dir == -1) {
			return;
		}

		int newDir = dir, newR = -1, newC = -1, f2 = -1;
		while (true) {
			newR = r + dr[newDir];
			newC = c + dc[newDir];
			if (newR >= 0 && newR < 4 && newC >= 0 && newC < 4 && map[newR][newC] != 100) {
				f2 = map[newR][newC];
				break;
			}
			newDir++;
			if (newDir > 8) {
				newDir = 1;
			}
		}

		map[newR][newC] = f1;
		map[r][c] = f2;

		fish[f1][0] = newR;
		fish[f1][1] = newC;
		fish[f1][2] = newDir;

		fish[f2][0] = r;
		fish[f2][1] = c;
	}

	static void sharkDFS(int sharkR, int sharkC, int sharkDir, int ate) {
		for (int i = 1; i <= 16; i++) {
			moveFish(i);
		}

		int[][] currMap = new int[4][4];
		deepCopy(currMap, map);
		
		int[][] currFish = new int[17][3];
		deepCopy(currFish, fish);

		boolean canEat = false;
		int newR = sharkR, newC = sharkC, fishNum = -1, fishDir = -1;
		while (true) {
			newR += dr[sharkDir];
			newC += dc[sharkDir];
			if (newR >= 0 && newR < 4 && newC >= 0 && newC < 4) {
				fishNum = map[newR][newC];
				fishDir = fish[fishNum][2];
				if (fishDir != -1) {
					canEat = true;

					map[newR][newC] = 100;
					map[sharkR][sharkC] = fishNum;
					fish[fishNum][2] = -1;

					sharkDFS(newR, newC, fishDir, (ate + fishNum));

					deepCopy(map, currMap);
					deepCopy(fish, currFish);

				}
			} else {
				break;
			}
		}

		if (!canEat) {
			max = Math.max(max, ate);
		}
	}

	static void deepCopy(int[][] newArr, int[][] originArr) {
		for(int i = 0 ; i < newArr.length ; i++) {
			newArr[i] = Arrays.copyOf(originArr[i], originArr[i].length);
		}
	}
}