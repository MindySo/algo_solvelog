import java.util.*;

class Solution {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static List<Integer> islands;
    static int blank = 'X' - '0';
    
    public int[] solution(String[] maps) {
        n = maps.length;
        m = maps[0].length();
        map = new int[n][m];
        visited = new boolean[n][m];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                map[i][j] = maps[i].charAt(j) - '0';
            }
        }
        
        islands = new ArrayList();
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(visited[i][j]) continue;
                
                if(map[i][j] != blank){
                    islands.add(getIsland(i, j));
                }
            }
        }
        
        if(islands.size() == 0){
            return new int[] {-1};
        }
        
        int[] answer = new int[islands.size()];
        for(int i = 0; i < islands.size(); i++){
            answer[i] = islands.get(i);
        }
        
        Arrays.sort(answer);
        
        return answer;
    }
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    public int getIsland(int r, int c){
        int sum = 0;
        Queue<Integer> que = new ArrayDeque();
        que.add(r * m + c);
        
        int curr, newR, newC;
        while(!que.isEmpty()){
            curr = que.poll();
            r = curr / m;
            c = curr % m;
            
            if(visited[r][c]) continue;
            
            visited[r][c] = true;
            sum += map[r][c];
            
            for(int d = 0; d < 4; d++){
                newR = r + dr[d];
                newC = c + dc[d];
                
                if(newR >= 0 && newR < n && newC >= 0 && newC < m
                  && !visited[newR][newC] && map[newR][newC] != blank){
                    que.add(newR * m + newC);
                }
            }
        }

        return sum;
    }
}