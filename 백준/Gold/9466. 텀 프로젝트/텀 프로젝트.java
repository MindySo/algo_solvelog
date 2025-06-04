import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());

		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine().trim());
			int[] graph = new int[N + 1];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i < N + 1; i++) {
				graph[i] = Integer.parseInt(st.nextToken());
			}

			// byte로 하면 메모리 1/4
			byte[] visited = new byte[N + 1]; // 0 = 방문 안함, 1 = 방문함, 2 = true, 3 = false
			Deque<Integer> stk = new ArrayDeque<>();
			int cnt = 0;
			
			int next;
			for (int i = 1; i < N + 1; i++) {
				if (visited[i] != 0) {
					continue;
				}

				stk.push(i);
				visited[i] = 1;
				
				while (true) {
					next = graph[stk.peek()];
					if (visited[next] == 0) {
						visited[next] = 1;	// 방문 표시
						stk.push(next);
					} else {
						break;
					}
				}

				int check;
				if (visited[next] == 1) { // true나 false가 아니고 방문만 함 : 스택에서 새로운 사이클 발견
					do {
						check = stk.pop();
						visited[check] = 2;		// 사이클(true) 표시
						cnt++;
					} while (check != next);

				} else { // true : 스택에서 사이클 또는 사이클이 아닌 노드를 만남
					do {
						check = stk.pop();		// true나 false는 그냥 보냄
					} while (visited[check] != 1);

					visited[check] = 3;
				}
				
				while(!stk.isEmpty()) {
					visited[stk.pop()] = 3;	// 나머지는 모두 사이클 아님(false) 표시 
				}
				
			}	// for
			System.out.println(N - cnt);
		}
	}
}
