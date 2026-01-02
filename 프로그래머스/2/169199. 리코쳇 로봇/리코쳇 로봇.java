import java.util.*;
class Solution {
    static int n, m, startR, startC, goal;
    static int[][][] boardMap;
    static boolean[][][] visited;
    static int[] dr = {0, -1, 0, 0, 1};
    static int[] dc = {0, 0, -1, 1, 0};
    
    public int solution(String[] board) {
        n = board.length;
        m = board[0].length();
        boardMap = new int[n][m][5];
        visited = new boolean[n][m][5];
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                Arrays.fill(boardMap[i][j], -1);
                boardMap[i][j][0] = board[i].charAt(j);
                if(boardMap[i][j][0] == 'R'){
                    startR = i;
                    startC = j;
                }
            }
        }
    
        int answer = Integer.MAX_VALUE;
        int r, c, dir, newR, newC, depth, next;
        int[] curr;
        
        Queue<int[]> que = new ArrayDeque();
        for(int d = 1; d < 5; d++){
            que.add(new int[] {startR, startC, d, 0});        
        }
        
        while(!que.isEmpty()){
            curr = que.poll();
            r = curr[0];
            c = curr[1];
            dir = curr[2];
            depth = curr[3];
            
            if(visited[r][c][dir]){
                continue;
            }
            visited[r][c][dir] = true;
            
            if(boardMap[r][c][0] == 'G'){
                answer = depth;
                break;
            } else {
                for(int d = 1; d < 5; d++){
                    next = DFS(r + dr[d], c + dc[d], d);
                    for(int nextD = 1; nextD < 5; nextD++){
                        que.add(new int[] {next / m, next % m, nextD, depth + 1});
                    }
                }
            }
        }
        
        if(answer == Integer.MAX_VALUE){
            answer = -1;
        }
        
        return answer;
    }
    
    public int DFS(int r, int c, int d){
        if(r < 0 || r >= n || c < 0 || c >= m || boardMap[r][c][0] == 'D'){
            return (r - dr[d]) * m + (c - dc[d]);
        }else if(boardMap[r][c][d] != -1){
            return boardMap[r][c][d];
        }else{
            return boardMap[r][c][d] = DFS(r + dr[d], c + dc[d], d);
        }
    }
}