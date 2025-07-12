import java.io.*;
import java.util.*;

class Solution {
    public int solution(int[][] info, int n, int m) {
        // [도둑들이 i까지 훔쳤을 때][B의 누적 흔적] = A의 최소 누적 흔적
        int dp[][] = new int[info.length][m + 1];
        for(int i = 0; i < info.length; i++){
            Arrays.fill(dp[i], 130);
        }
        
        int traceA = info[0][0];
        int traceB = info[0][1];
        dp[0][0] = traceA;
        if(traceB < m){
            dp[0][traceB] = 0;
        }
        
        for(int i = 1; i < info.length; i++){
            traceA = info[i][0];
            traceB = info[i][1];
            
            for(int j = 0; j < m; j++){
                // A가 훔침 / B가 안 훔침
                dp[i][j] = Math.min(dp[i][j], dp[i - 1][j] + traceA);
                
                // A가 안 훔침 / B가 훔침
                if(j + traceB < m){
                    dp[i][j + traceB] = Math.min(dp[i][j + traceB], dp[i - 1][j]);
                }
            }
        }
        
        int answer = 130;
        for(int i = 0; i < m; i++){
            answer = Math.min(answer, dp[info.length - 1][i]);
        }
        
        if(answer >= n){
            answer = -1;
        }
        
        return answer;
    }
}