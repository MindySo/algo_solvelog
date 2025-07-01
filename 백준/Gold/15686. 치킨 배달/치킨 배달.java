import java.io.*;
import java.util.*;

public class Main {
	static class House {
		int r, c, dist, distChic;

		House(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static class Chic {
		int r, c;
		boolean open;

		Chic(int r, int c, boolean open) {
			this.r = r;
			this.c = c;
			this.open = open;
		}
	}

	static int N, M, originDist, newDist, minDist;
	static List<House> houses;
	static List<Chic> chics;

	public static void main(String[] args) throws IOException {
		getInput();

		originDist = 0;
		getOriginDist();
		
		minDist = Integer.MAX_VALUE;
		combo(0, 0);
		
		System.out.println(minDist);

	}// main();

	static void getInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		houses = new ArrayList<>();
		chics = new ArrayList<>();
		originDist = 0;

		int num;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				num = Integer.parseInt(st.nextToken());
				if (num == 1) {
					houses.add(new House(i, j));
				} else if (num == 2) {
					chics.add(new Chic(i, j, false));
				}
			}
		}
	}// getInput();

	static void getOriginDist() {
		for (House house : houses) {
			house.dist = Integer.MAX_VALUE;

			Chic chic;
			int curr;
			for (int i = 0; i < chics.size(); i++) {
				chic = chics.get(i);
				curr = Math.abs(chic.r - house.r) + Math.abs(chic.c - house.c);
				if (curr < house.dist) {
					house.dist = curr;
					house.distChic = i;
				}
			}
			originDist += house.dist;
		}
	}// getOriginDist()

	static void getNewDist(House house) {
		// 가장 가까운 치킨집이 폐업하지 않았다면 pass
		if (chics.get(house.distChic).open) {
			return;
		}

		int min = Integer.MAX_VALUE;
		int curr;
		for (Chic chic : chics) {
			if (chic.open) {
				curr = Math.abs(chic.r - house.r) + Math.abs(chic.c - house.c);
				min = Math.min(min, curr);
			}
		}

		newDist = newDist - house.dist + min;
	}// getNewDist()

	static void combo(int idx, int open) {
		if(open ==  M) {
			newDist = originDist;
			for(House h : houses) {
				getNewDist(h);
			}
			minDist = Math.min(minDist, newDist);
			return;
		}
		
		if(idx == chics.size()) {
			return;
		}

		chics.get(idx).open = true;
		combo(idx + 1, open + 1);

		chics.get(idx).open = false;
		combo(idx + 1, open);
	}// combo()
}