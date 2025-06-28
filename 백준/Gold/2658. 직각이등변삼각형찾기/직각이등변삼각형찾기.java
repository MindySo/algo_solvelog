import java.io.*;
import java.util.*;

public class Main {
	static int[][] map;
	static int[][] cnt; // [0]가로합 [1]세로합
	static int top, bottom, left, right, maxR, minR, maxC, minC;
	static PriorityQueue<int[]> dots;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		cnt = new int[2][10];
		String str;
		for (int i = 0; i < 10; i++) {
			str = br.readLine();
			for (int j = 0; j < 10; j++) {
				map[i][j] = str.charAt(j) - '0';
				cnt[0][i] += map[i][j];
				cnt[1][j] += map[i][j];
			}
		}

		dots = new PriorityQueue<int[]>(new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				if (o1[0] != o2[0]) {
					return o1[0] - o2[0];
				} else {
					return o1[1] - o2[1];
				}
			}
		});

		int diagNum = diag(0);
		if(diagNum == diag(1) && diagNum != -1){
			dots.add(new int[] {maxR, maxC});
			dots.add(new int[] {maxR, minC});
			dots.add(new int[] {minR, maxC});
		}else if (height(0) && width(1)) {
			dots.add(new int[] { top, (left + right) / 2 });
			dots.add(new int[] { bottom, left });
			dots.add(new int[] { bottom, right });
		} else if (height(1) && width(0)) {
			dots.add(new int[] { (left + right) / 2, top });
			dots.add(new int[] { left, bottom });
			dots.add(new int[] { right, bottom });
		}else {
			System.out.println(0);
			return;
		}

		int[] temp;
		for (int i = 0; i < 3; i++) {
			temp = dots.poll();
			System.out.println(temp[0] + " " + temp[1]);
		}

	}

	static boolean height(int dir) {
		int idx = 0;
		int num = -1;
		int start = -1, end = -1;
		while (idx < 10) {
			if (cnt[dir][idx] == 0) {
				idx++;
			} else if (cnt[dir][idx] % 2 == 1) {
				num = cnt[dir][idx];
				break;
			} else {
				return false;
			}
		}

		if (num == -1) {
			return false;
		} else if (num == 1) {
			start = idx;
			while (++idx < 10) {
				num += 2;
				if (cnt[dir][idx] == num) {
					continue;
				} else if (cnt[dir][idx] == 0) {
					break;
				} else {
					return false;
				}
			}
			end = idx - 1;
		} else {
			end = idx;
			do {
				if (cnt[dir][idx] != num) {
					return false;
				}
				num -= 2;
				idx++;
			} while (num >= 1 && idx < 10);
			start = idx - 1;
		}

		while (++idx < 10) {
			if (cnt[dir][idx] != 0) {
				return false;
			}
		}
		top = start + 1;
		bottom = end + 1;
		return true;
	}

	static boolean width(int dir) {
		int start = -1, end = -1;

		int idx = 0;
		while (idx < 10) {
			if (cnt[dir][idx] == 0) {
				idx++;
			} else if (cnt[dir][idx] == 1) {
				start = idx;
				break;
			} else {
				return false;
			}
		}

		idx = 9;
		while (idx >= 0) {
			if (cnt[dir][idx] == 0) {
				idx--;
			} else if (cnt[dir][idx] == 1) {
				end = idx;
				break;
			} else {
				return false;
			}
		}
		if ((end - start) % 2 != 0 || end == start || start == -1 || end == -1) {
			return false;
		}
		
		int num = 1;
		int s = start;
		int e = end;
		while (s <= e) {
			if (cnt[dir][s++] == num && cnt[dir][e--] == num) {
				num++;
				continue;
			} else {
				return false;
			}
		}
		left = start + 1;
		right = end + 1;
		return true;
	}

	static int diag(int dir) {
		int idx = 0;
		int num = -1;
		int start = -1, end = -1;
		while (idx < 10) {
			if (cnt[dir][idx] == 0) {
				idx++;
			} else {
				num = cnt[dir][idx];
				break;
			}
		}

		if (num == -1) {
			return -1;
		} else if (num == 1) {
			start = idx;
			while (++idx < 10) {
				if (cnt[dir][idx] == ++num) {
					continue;
				} else if (cnt[dir][idx] == 0) {
					break;
				} else {
					return -1;
				}
			}
			end = idx - 1;
		} else {
			end = idx;
			do {
				if (cnt[dir][idx] != num--) {
					return -1;
				}
				idx++;
			} while (num >= 1 && idx < 10);
			start = idx - 1;
		}
		
		if(cnt[dir][end] == 1) {
			return -1;
		}
		
		while (++idx < 10) {
			if (cnt[dir][idx] != 0) {
				return -1;
			}
		}

		if (dir == 0) {
			minR = start + 1;
			maxR = end + 1;
		} else {
			minC = start + 1;
			maxC = end + 1;
		}

		return cnt[dir][end];
	}
}