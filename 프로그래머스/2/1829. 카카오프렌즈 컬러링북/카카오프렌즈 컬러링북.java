import java.util.*;

class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        Map<Integer, Integer> area = new HashMap();
        boolean[][] visited = new boolean[m][n];
        Queue<Integer> que = new ArrayDeque();
        int[] dr = new int[] {-1, 0, 0, 1};
        int[] dc = new int[] {0, -1, 1, 0};
        int num = 0;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                int color = picture[i][j];
                if(visited[i][j] || color == 0) continue;
                
                area.put(num, 0);
                que.add(i * n + j);

                while(!que.isEmpty()){
                    int temp = que.poll();
                    int r = temp / n;
                    int c = temp % n;

                    if(visited[r][c]) continue;
                    visited[r][c] = true;
                    area.put(num, area.get(num) + 1);

                    for(int d = 0; d < 4; d++){
                        int newR = r + dr[d];
                        int newC = c + dc[d];
                        if(newR >= 0 && newR < m && newC >= 0 && newC < n 
                           && !visited[newR][newC] && picture[newR][newC] == color)
                            que.add(newR * n + newC);
                    }
                }

                maxSizeOfOneArea = Math.max(maxSizeOfOneArea, area.get(num++));
            }
        }
        
        return new int[] {area.size(), maxSizeOfOneArea};
    }
}