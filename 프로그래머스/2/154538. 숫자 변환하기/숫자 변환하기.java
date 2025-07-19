import java.util.*;

class Solution {
    public int solution(int x, int y, int n) {
        Queue<int[]> que = new LinkedList<>();
        boolean[] visited = new boolean[y + 1];
        que.add(new int[]{x, 0});
        visited[x] = true;

        while (!que.isEmpty()) {
            int[] cur = que.poll();
            int val = cur[0];
            int cnt = cur[1];
            if (val == y) {
                return cnt;
            }
                
            int[] nextVals = {val + n, val * 2, val * 3};
            for (int next : nextVals) {
                if (next <= y && !visited[next]) {
                    visited[next] = true;
                    que.add(new int[]{next, cnt + 1});
                }
            }
        }
        return -1;
    }
}
