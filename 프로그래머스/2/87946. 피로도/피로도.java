import java.util.*;

class Solution {
    static boolean[] visited;
    static int[][] dgArr;
    static int max;
    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];
        dgArr = dungeons;
        max = 0;
        
        for(int i = 0; i < dgArr.length; i++){
            max = Math.max(max, DFS(i, k, 0));
        }
        
        return max;
    }
    
    public int DFS(int idx, int tired, int cnt){
        if(tired < dgArr[idx][0]) return cnt;
        
        int newMax = cnt + 1;
        visited[idx] = true;
        for(int i = 0; i < dgArr.length; i++){
            if(!visited[i]){
                int newCnt = DFS(i, tired - dgArr[idx][1], cnt + 1);
                newMax = Math.max(newMax, newCnt);
            }
        }
        visited[idx] = false;
        return newMax;
    }
}