import java.util.*;
class Solution {
    static int N;
    static int M;
    static int[][] costs;
    static int[][] hints;
    static int[] bought;
    static int answer;
    
    public int solution(int[][] cost, int[][] hint) {
        N = cost.length;
        M = cost[0].length;
        costs = cost;
        hints = hint;
        bought = new int[N];
        answer = Integer.MAX_VALUE;
        
        DFS(0, 0);
        
        return answer;
    }
    
    public void DFS(int stage, int total){
        int useHint = bought[stage] < M ? 
            costs[stage][bought[stage]] : costs[stage][M - 1];
 
        if(stage == N - 1){
            answer = Math.min(answer, total + useHint);
            return;
        }
        
        DFS(stage + 1, total + useHint);
        
        for(int i = 1; i < hints[stage].length; i++) bought[hints[stage][i] - 1]++;
        DFS(stage + 1, total + hints[stage][0] + useHint);
        for(int i = 1; i < hints[stage].length; i++) bought[hints[stage][i] - 1]--;
    }
}