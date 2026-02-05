class Solution {
    static int[][] map;
    public int[] solution(int[][] arr) {
        map = arr;
        int end = map.length - 1;
        
        return DFS(0, 0, end, end);
    }
    
    public int[] DFS(int fromR, int fromC, int toR, int toC){
        if(fromR == toR){
            if(map[fromR][fromC] == 0){
                return new int[] {1, 0};
            }else{
                return new int[] {0, 1};
            }
        }
        
        int[] cnt = new int[2];
        int[] quad;
        int midR = (fromR + toR) / 2;
        int midC = (fromC + toC) / 2;
        
        quad = DFS(fromR, fromC, midR, midC);
        cnt[0] += quad[0];
        cnt[1] += quad[1];
        
        quad = DFS(fromR, midC + 1, midR, toC);
        cnt[0] += quad[0];
        cnt[1] += quad[1];
        
        quad = DFS(midR + 1, fromC, toR, midC);
        cnt[0] += quad[0];
        cnt[1] += quad[1];
        
        quad = DFS(midR + 1, midC + 1, toR, toC);
        cnt[0] += quad[0];
        cnt[1] += quad[1];
        
        if(cnt[1] == 0) cnt[0] = 1;
        if(cnt[0] == 0) cnt[1] = 1;
        
        return cnt;
    }
}