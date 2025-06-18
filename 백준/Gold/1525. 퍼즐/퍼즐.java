import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static Set<Integer> visited;
	static int min;
	public static void main(String[] args) throws IOException {
		map = new int[3][3];
		visited = new HashSet<>();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int zeroR = -1, zeroC = -1;
		long mapNum = 0;
		for(int i = 0 ; i < 3 ; i++) {
			st =  new StringTokenizer(br.readLine());
			for(int j = 0 ; j < 3 ; j ++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 0) {
					num = 9;
					zeroR = i;
					zeroC = j;
				}
				mapNum += num;
				mapNum *= 10;
			}
		}
		
		min = -1;
		BFS((zeroR * 3 + zeroC), (int)(mapNum / 10), 0);
		
		System.out.println(min);
	}
	
	static int[] dr = {-1, 0, 0, 1};
	static int[] dc = {0, 1, -1, 0};
	
	static void BFS(int zero, int mapNum, int depth) {
		Queue<int[]> que = new LinkedList<>();
		que.add(new int[] {zero, mapNum, depth});
		
		int[] temp;
		while(!que.isEmpty()) {
			temp = que.poll();
			zero = temp[0];
			mapNum = temp[1];
			depth = temp[2];
			
			if(mapNum == 123456789) {
				min = depth;
				return;
			}

			if(visited.contains(mapNum)) {
				continue;
			}
			
			visited.add(mapNum);
			
			int zeroR = zero / 3;
			int zeroC = zero % 3;

			int r, c, move, newMapNum;
			for(int d = 0 ; d < 4 ; d++) {
				r = zeroR + dr[d];
				c = zeroC + dc[d];

				if(r >= 0 && r < 3 && c >= 0 && c < 3) {
					getMap(mapNum);

					move = map[r][c];
					map[r][c] = 9;
					map[zeroR][zeroC] = move;
					
					newMapNum = getMapNum();
					que.add(new int[] {(r * 3 + c), newMapNum, (depth + 1)});
				}
			}
			
		}
	}
	
	static void getMap(int mapNum) {
		for(int i = 2 ; i >= 0 ; i--) {
			for(int j = 2 ; j >= 0 ; j--) {
				map[i][j] = mapNum % 10;
				mapNum /= 10;
			}
		}
	}
	
	static int getMapNum() {
		long num = 0;
		for(int i = 0 ; i < 3 ; i++) {
			for(int j = 0 ; j < 3 ; j++) {
				num += map[i][j];
				num *= 10;
			}
		}
		return (int)(num / 10);
	}
}