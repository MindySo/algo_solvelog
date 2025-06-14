import java.io.*;
import java.util.*;

public class Main {
	static int N, M, K;
	static List<Integer>[][] tree;
	static int[][] nutri;
	static int[][] S2D2;

	public static void main(String[] args) throws IOException {
		getInput();
		
		for(int i = 0 ; i < K ; i++) {
			springAndSummer();
			autumn();
			winter();
		}
		
		int ans = count();
		System.out.println(ans);
	}

	static void getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		tree = new ArrayList[N + 1][N + 1];
		nutri = new int[N + 1][N + 1];
		S2D2 = new int[N + 1][N + 1];

		for (int i = 1; i <= N; i++) {
			Arrays.fill(nutri[i], 5);
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				S2D2[i][j] = Integer.parseInt(st.nextToken());
				tree[i][j] = new ArrayList<Integer>();
			}
		}

		int r, c, age;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			c = Integer.parseInt(st.nextToken());
			age = Integer.parseInt(st.nextToken());
			tree[r][c].add(age);
		}
	} // getInput()

	static List<int[]> breed;

	static void springAndSummer() {
		breed = new ArrayList<>();
		for (int i = 1; i <= N ; i++) {
			for (int j = 1; j <= N ; j++) {
				int fertilize = 0;
				for (int idx = tree[i][j].size() - 1 ; idx >= 0; idx--) {
					int age = tree[i][j].get(idx);
					// 나무 나이만큼 양분이 존재한다면 양분을 먹고 나이가 1 증가
					if (nutri[i][j] >= age) {
						nutri[i][j] -= age;
						tree[i][j].set(idx, ++age);

						// 5의 배수이면 번식할 수 있다.
						if (age % 5 == 0) {
							breed.add(new int[] { i, j });
						}
					} else {
						// 여름 : 양분이 부족하면 나무는 죽은걸로 처리
						fertilize += age / 2;
						tree[i][j].remove(idx);
					}
				}	// for(tree)
				
				nutri[i][j] += fertilize;
			}
		}
	}

	static int[] dr = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dc = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static void autumn() {
		int[] breedTree;
		int r, c, newR, newC;
		for (int idx = 0; idx < breed.size(); idx++) {
			breedTree = breed.get(idx);
			r = breedTree[0];
			c = breedTree[1];

			for (int d = 0; d < 8; d++) {
				newR = r + dr[d];
				newC = c + dc[d];

				if (newR >= 1 && newR <= N && newC >= 1 && newC <= N) {
					tree[newR][newC].add(1);
				}
			}
		}
	}

	static void winter() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				nutri[i][j] += S2D2[i][j];
			}
		}
	}
	
	static int count() {
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				cnt += tree[i][j].size();
			}
		}
		return cnt;
	}
}
