import java.util.*;
class Solution {
    static int N;
    static int[][] map;
    static Set<String> visited;
    static Queue<int[]> que;
    
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, 1, 1, 1, 0, -1, -1, -1};
    
    public int solution(int[][] board) {
        N = board.length;
        map = board;
        visited = new HashSet();

        que = new ArrayDeque();
        que.add(new int[] {0, 0, 0, 1, 0});
        int answer = 0;
        
        while(!que.isEmpty()){
            int[] curr = que.poll();
            int r1 = curr[0];
            int c1 = curr[1];
            int r2 = curr[2];
            int c2 = curr[3];
            int time = curr[4];
            
            if((r1 == N - 1 && c1 == N - 1) || (r2 == N - 1 && c2 == N - 1)){
                answer = time;
                break;
            }

            String code = encode(r1, c1, r2, c2);
            if(visited.contains(code)) continue;
            visited.add(code);
            
            int newR1, newC1, newR2, newC2;
            for(int d = 0; d < 8; d += 2){
                newR1 = r1 + dr[d];
                newC1 = c1 + dc[d];
                newR2 = r2 + dr[d];
                newC2 = c2 + dc[d];
                
                if(check(newR1, newC1) && check(newR2, newC2)){
                    String newCode = encode(newR1, newC1, newR2, newC2);
                    if(!visited.contains(newCode)) 
                        que.add(new int[] {newR1, newC1, newR2, newC2, time + 1});
                }
                
                if(newR1 == r2 && newC1 == c2){
                    rotate(r1, c1, (d + 6) % 8, (d + 7) % 8, time);
                    rotate(r1, c1, (d + 2) % 8, (d + 1) % 8, time);
                }
                
                if(newR2 == r1 && newC2 == c1){
                    rotate(r2, c2, (d + 6) % 8, (d + 7) % 8, time);
                    rotate(r2, c2, (d + 2) % 8, (d + 1) % 8, time);
                }
            }
        }

        return answer;
    }
    
    public boolean check(int r, int c){
        if(r >= 0 && r < N && c >= 0 && c < N && map[r][c] == 0) return true;
        return false;
    }
    
    public String encode(int r1, int c1, int r2, int c2){
        if(r1 != r2){
            if(r1 < r2) return r1 + " " + c1 + " " + r2 + " " + c2;
            return r2 + " " + c2 + " " + r1 + " " + c1;
        }else{
            if(c1 < c2) return r1 + " " + c1 + " " + r2 + " " + c2;
            return r2 + " " + c2 + " " + r1 + " " + c1;
        }
    }
    
    public void rotate(int r, int c, int dir, int checkDir, int time){
        int rtR = r + dr[dir];
        int rtC = c + dc[dir];
        int checkR = r + dr[checkDir];
        int checkC = c + dc[checkDir];
        
        if(check(rtR, rtC) && check(checkR, checkC) && 
           !visited.contains(encode(r, c, rtR, rtC)))
            que.add(new int[] {r, c, rtR, rtC, time + 1});
    }
}