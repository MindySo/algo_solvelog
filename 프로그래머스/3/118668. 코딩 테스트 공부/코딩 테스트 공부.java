import java.util.*;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int targetAl = 0, targetCo = 0;
        
        Set<Problem> pros = new HashSet();
        for(int[] p : problems){
            Problem pro = new Problem(p[0], p[1], p[2], p[3], p[4]);
            targetAl = Math.max(targetAl, pro.al_req);
            targetCo = Math.max(targetCo, pro.co_req);
            pros.add(pro);
        }
        
        int maxAl = Math.max(alp, targetAl);
        int maxCo = Math.max(cop, targetCo);
        
        int[][] dp = new int[maxAl + 1][maxCo + 1];
        for(int[] row : dp) Arrays.fill(row, 300000);
        dp[alp][cop] = 0;
        
        for(int al = alp; al <= maxAl; al++){
            for(int co = cop; co <= maxCo; co++){
                if(al + 1 <= maxAl) dp[al+1][co] = Math.min(dp[al+1][co], dp[al][co] + 1);
                if(co + 1 <= maxCo) dp[al][co+1] = Math.min(dp[al][co+1], dp[al][co] + 1);
                
                for(Problem p : pros) {
                    if(al < p.al_req || co < p.co_req) continue;
                    int nextAl = Math.min(maxAl, al + p.al_rwd);
                    int nextCo = Math.min(maxCo, co + p.co_rwd);
                    dp[nextAl][nextCo] = Math.min(dp[nextAl][nextCo], dp[al][co] + p.cost);
                }
            }
        }
        
        return dp[maxAl][maxCo];
    }
    
    class Problem{
        int al_req, co_req, al_rwd, co_rwd, cost;
        public Problem(int al_req, int co_req, int al_rwd, int co_rwd, int cost){
            this.al_req = al_req;
            this.co_req = co_req;
            this.al_rwd = al_rwd;
            this.co_rwd = co_rwd;
            this.cost = cost;
        }
    }
}