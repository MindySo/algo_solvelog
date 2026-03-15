import java.util.*;
class Solution {
    static Set<Integer>[] wins;
    static Set<Integer>[] loses;
    public int solution(int n, int[][] results) {
        wins = new Set[n + 1];
        loses = new Set[n + 1];
        
        for(int i = 1; i <= n; i++){
            wins[i] = new HashSet();
            loses[i] = new HashSet();
        }
        
        for(int[] result : results){
            DFS(result[0], result[1]);
        }
        
        int answer = 0;
        for(int i = 1; i <= n; i++) if(wins[i].size() + loses[i].size() == n - 1) answer++;
        
        return answer;
    }
    
    public void DFS(int winnerId, int loserId){
        wins[winnerId].add(loserId);
        loses[loserId].add(winnerId);
        
        for(int winner : loses[winnerId])
            if(!wins[winner].contains(loserId)) DFS(winner, loserId);
        
        for(int loser : wins[loserId])
            if(!loses[loser].contains(winnerId)) DFS(winnerId, loser);
    }
}