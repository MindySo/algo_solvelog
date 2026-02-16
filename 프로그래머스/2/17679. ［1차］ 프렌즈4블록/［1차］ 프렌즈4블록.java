import java.util.*;
class Solution {
    static char[][] map;
    static Set<Integer> blockSet;
    static Set<Integer> fillSet;
    static int M, N, answer;
    public int solution(int m, int n, String[] board) {
        map = new char[m][n];
        blockSet = new HashSet();
        fillSet = new HashSet();
        M = m;
        N = n;
        answer = 0;
        
        for(int i = 0; i < m; i++){
            for(int j = 0; j < n; j++){
                map[i][j] = board[i].charAt(j);
            }
        }
        
        while(match());
        
        return answer;
    }
    
    static int[] dr = {0, 1, 1};
    static int[] dc = {1, 0, 1};
    
    public boolean match(){
        int curr, newR, newC;
        
        for(int r = 0; r < M - 1; r++){
            blockCheck:
            for(int c = 0; c < N - 1; c++){

                if(map[r][c] != 0){
                    for(int d = 0; d < 3; d++){
                        newR = r + dr[d];
                        newC = c + dc[d];
                        if(map[newR][newC] != map[r][c]) continue blockCheck;
                    }
                    
                    blockSet.add(r * N + c);
                    for(int d = 0; d < 3; d++){
                        newR = r + dr[d];
                        newC = c + dc[d];
                        blockSet.add(newR * N + newC);
                    }
                }
            }
        }
        
        if(blockSet.isEmpty()) return false;
        
        int r, c;
        for(int num : blockSet){
            r = num / N;
            c = num % N;
            map[r][c] = 0;
            fillSet.add(c);
        }
        
        answer += blockSet.size();
        blockSet.clear();
        fill();
        
        return true;
    }
    
    public void fill(){
        Stack<Character> stk = new Stack();
        for(int c : fillSet){
            for(int r = 0; r < M; r++){
                if(map[r][c] != 0) stk.push(map[r][c]);
            }
            
            int r = M - 1;
            while(!stk.isEmpty()){
                map[r--][c] = stk.pop();
            }
            while(r >= 0) map[r--][c] = 0;
        }
        fillSet.clear();
    }
}