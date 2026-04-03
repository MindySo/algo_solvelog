import java.util.*;
class Solution {
    public int[] solution(int m, int n, int h, int w, int[][] drops) {
        int[][] board = new int[m][n];
        for(int i = 0; i < m; i++) Arrays.fill(board[i], Integer.MAX_VALUE);
        for(int d = 0; d < drops.length; d++) board[drops[d][0]][drops[d][1]] = d;
        
        int[][] minC = new int[m][n];
        Deque<Integer> deq = new ArrayDeque();
        for(int r = m - 1; r >= 0; r--){
            deq.clear();
            for(int c = n - 1; c >= 0; c--){
                if(!deq.isEmpty() && deq.getFirst() == c + w) deq.removeFirst();
                while(!deq.isEmpty() && board[r][deq.getLast()] >= board[r][c]) 
                    deq.removeLast();
                deq.addLast(c);
                minC[r][c] = board[r][deq.getFirst()];
            }
        }
        
        int[][] min = new int[m][n];
        for(int c = n - 1; c >= 0; c--){
            deq.clear();
            for(int r = m - 1; r >= 0; r--){
                if(!deq.isEmpty() && deq.getFirst() == r + h) deq.removeFirst();
                while(!deq.isEmpty() && minC[deq.getLast()][c] >= minC[r][c]) 
                    deq.removeLast();
                deq.addLast(r);
                min[r][c] = minC[deq.getFirst()][c];
            }
        }
        
        int max = 0, maxR = 0, maxC = 0;
        
        found:
        for(int i = 0; i <= m - h; i++){
            for(int j = 0; j <= n - w; j++){
                if(min[i][j] > max){
                    max = min[i][j];
                    maxR = i;
                    maxC = j;
                }
                if(max == Integer.MAX_VALUE) break found;
            }
        }
        
        return new int[] {maxR, maxC};
    }
}