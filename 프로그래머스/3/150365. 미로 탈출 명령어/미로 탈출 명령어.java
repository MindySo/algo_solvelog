import java.util.*;
class Solution {
    class Pos{
        int i;
        int j;
        String str;
        
        Pos(int i, int j, String str){
            this.i = i;
            this.j = j;
            this.str = str;
        }
    }
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        x--; y--; r--; c--;
        
        char[] dir = {'d', 'l', 'r', 'u'};
        int[] dr = {1, 0, 0, -1};
        int[] dc = {0, -1, 1, 0};
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < k; i++) sb.append('z');
        
        String answer = sb.toString();
        sb = new StringBuilder();
        
        int len = 0;
        Queue<Pos> que = new ArrayDeque();
        
        while(sb.length() < k){
            boolean[][] visited = new boolean[n][m];
            
            que.clear();
            que.add(new Pos(x, y, ""));
            
            while(!que.isEmpty()){
                Pos pos = que.poll();
                if(pos.i == r && pos.j == c && len + pos.str.length() == k){
                    String newStr = sb.toString() + pos.str;
                    if(answer.compareTo(newStr) > 0) answer = newStr;
                    break;
                }
                
                if(pos.str.length() > k) break;
                
                if(visited[pos.i][pos.j]) continue;
                visited[pos.i][pos.j] = true;
                
                for(int d = 0; d < 4; d++){
                    int newX = pos.i + dr[d];    
                    int newY = pos.j + dc[d];    
                    if(newX >= 0 && newX < n && newY >= 0 && newY < m 
                       && !visited[newX][newY]) que.add(new Pos(newX, newY, pos.str + dir[d]));
                }                
            }
            
            if(x + 1 < n){
                x++;
                sb.append("d");
            }else if(y > 0){
                y--;
                sb.append("l");                
            }else{
                y++;
                sb.append("r");
            }
            len++;
        }
        
        return answer.charAt(0) == 'z' ? "impossible" : answer;
    }
}