import java.util.*;
class Solution {
    public int solution(int[][] maps) {
        int N = maps.length;
        int M = maps[0].length;
        boolean[][] visited = new boolean[N][M];
        
        int[] dr = {-1 ,0, 0, 1};
        int[] dc = {0, -1, 1, 0};
        
        Queue<int[]> que = new ArrayDeque();
        que.add(new int[] {0, 0, 1});
        
        int answer = -1;
        int[] temp;
        int r, c, passed, newR, newC;
        while(!que.isEmpty()){
            temp = que.poll();
            r = temp[0];
            c = temp[1];
            passed = temp[2];
            
            if(r == N - 1 && c == M - 1){
                answer = passed;
                break;
            }
            
            if(visited[r][c]) continue;
            visited[r][c] = true;
            
            for(int d = 0; d < 4; d++){
                newR = r + dr[d];
                newC = c + dc[d];
                if(newR >= 0 && newR < N && newC >= 0 && newC < M
                  && maps[newR][newC] == 1 && !visited[newR][newC]) {
                    que.add(new int[] {newR, newC, passed + 1});
                }
            }
        }

        return answer;
    }
}