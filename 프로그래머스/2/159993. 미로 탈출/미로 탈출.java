import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int N = maps.length;
        int M = maps[0].length();
        char[][] map = new char[N][M];
        int startR = 0, startC = 0, elapsed = 0, finalTime = 0;

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = maps[i].charAt(j);
                if(map[i][j] == 'S'){
                    startR = i;
                    startC = j;
                }
            }
        }
        
        boolean visited[][] = new boolean[N][M];
        Queue<int[]> que = new ArrayDeque();
        que.add(new int[] {startR, startC, 0});
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, -1, 0, 1};
        int[] curr;
        int r, c, depth, newR, newC;
        
        while(!que.isEmpty()){
            curr = que.poll();
            r = curr[0];
            c = curr[1];
            depth = curr[2];
            
            if(visited[r][c]) continue;
            visited[r][c] = true;
            
            if(map[r][c] == 'L'){
                startR = r;
                startC = c;
                elapsed += depth;
                break;
            }
            
            for(int d = 0; d < 4; d++){
                newR = r + dr[d];
                newC = c + dc[d];
                if(newR >= 0 && newR < N && newC >= 0 && newC < M 
                   && !visited[newR][newC] && map[newR][newC] != 'X'){
                    que.add(new int[] {newR, newC, depth + 1});
                }
            }
        }
        
        if(elapsed == 0){
            return -1;
        }
        
        finalTime += elapsed;
        elapsed = 0;
        visited = new boolean[N][M];
        que.clear();
        que.add(new int[] {startR, startC, 0});
        
        while(!que.isEmpty()){
            curr = que.poll();
            r = curr[0];
            c = curr[1];
            depth = curr[2];
            
            if(visited[r][c]) continue;
            visited[r][c] = true;
            
            if(map[r][c] == 'E'){
                elapsed += depth;
                break;
            }
            
            for(int d = 0; d < 4; d++){
                newR = r + dr[d];
                newC = c + dc[d];
                if(newR >= 0 && newR < N && newC >= 0 && newC < M 
                   && !visited[newR][newC] && map[newR][newC] != 'X'){
                    que.add(new int[] {newR, newC, depth + 1});
                }
            }
        }
        
        if(elapsed == 0){
            return -1;
        }
        
        finalTime += elapsed;
        
        return finalTime;
    }
}