import java.util.*;
class Solution {
    public int solution(int[][] board){
        int n = board.length;
        int m = board[0].length;
        int[][] cnt = new int[n][m];
        int max = 0;
        
        if(n == 1 || m == 1) return 1;
        
        cnt[n - 1] = board[n - 1];
        for(int r = 0; r < n; r++) cnt[r][m - 1] = board[r][m - 1];
        
        for(int r = n - 2; r >= 0; r--){
            for(int c = m - 2; c >= 0; c--){
                int min = Integer.MAX_VALUE;
                
                if(board[r][c] == 1) {
                    min = Math.min(min, cnt[r + 1][c]);
                    min = Math.min(min, cnt[r][c + 1]);
                    min = Math.min(min, cnt[r + 1][c + 1]);

                    cnt[r][c] = min + 1;
                    max = Math.max(max, cnt[r][c]);
                }
            }
        }
        
        return max * max;
    }
}