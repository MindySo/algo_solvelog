class Solution {
    public int[] solution(int n) {
        int[][] map = new int[n][n];
        boolean end = false;
        int idx = 1;
        int r = 0;
        int c = 0;
        map[r][c] = idx;
        
        while(!end){
            end = true;
            while(r + 1 < n && map[r + 1][c] == 0){
                map[++r][c] = ++idx;
                end = false;
            }
            while(c + 1 < n && map[r][c + 1] == 0){
                map[r][++c] = ++idx;
                end = false;
            }
            while(r - 1 >= 0 && c - 1 >= 0 && map[r - 1][c - 1] == 0){
                map[--r][--c] = ++idx;
                end = false;
            }
        }
        
        int size = (n * n - n) / 2 + n;
        int[] answer = new int[size];
        idx = 0;
        
        for(int i = 0; i < n; i++){
            for(int j = 0; j <= i; j++){
                answer[idx++] = map[i][j];
            }
        }
        
        return answer;
    }
}