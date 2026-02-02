import java.util.*;

class Solution {
    public int solution(String dirs) {
        boolean[][] visited = new boolean[122][122];
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, 1, -1};
        
        int curr, r, c, nextR, nextC, d;
        int next = 60;
        int answer = 0;
        
        for(char dir : dirs.toCharArray()){
            curr = next;
            r = curr / 11;
            c = curr % 11;
            
            if(dir == 'U'){
                d = 0;
            }else if(dir == 'D'){
                d = 1;
            }else if(dir == 'R'){
                d = 2;
            }else{
                d = 3;
            }
            
            nextR = r + dr[d];
            nextC = c + dc[d];
            
            if(nextR >= 0 && nextR < 11 && nextC >= 0 && nextC < 11){
                next = nextR * 11 + nextC;
                if(!visited[curr][next]){
                    visited[curr][next] = true;
                    visited[next][curr] = true;
                    answer++;
                }
            }
        }
        
        return answer;
    }
}
