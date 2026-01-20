import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[][] map = new int[rows][columns];
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                map[i][j] = i * columns + j + 1;
            }
        }
        List<Integer> list = new ArrayList();
        
        for(int[] query : queries){
            int r1 = query[0] - 1;
            int c1 = query[1] - 1;
            int r2 = query[2] - 1;
            int c2 = query[3] - 1;
            int r = r1;
            int c = c1;
            
            int min = Integer.MAX_VALUE;
            int prev = map[r][c];
            int next;
            
            while(c < c2){
                min = Math.min(min, prev);
                next = map[r][++c];
                map[r][c] = prev;
                prev = next;
            }
            while(r < r2){
                min = Math.min(min, prev);
                next = map[++r][c];
                map[r][c] = prev;
                prev = next;
            }
            while(c > c1){
                min = Math.min(min, prev);
                next = map[r][--c];
                map[r][c] = prev;
                prev = next;
            }
            while(r > r1){
                min = Math.min(min, prev);            
                next = map[--r][c];
                map[r][c] = prev;
                prev = next;
            }
            list.add(min);
        }
        
        return list.stream().mapToInt(i -> i).toArray();
    }
}