import java.util.*;
class Solution {
    public int solution(int sticker[]) {
        int n = sticker.length;
        
        if(n == 1) return sticker[0];
        if(n == 2) return Math.max(sticker[0], sticker[1]);
        if(n == 3) return Math.max(sticker[0], Math.max(sticker[1], sticker[2]));
        
        int[] dp = new int[n];
        dp[0] = sticker[0];
        dp[1] = sticker[1];
        dp[2] = sticker[0] + sticker[2];
        
        for(int i = 3; i < n; i++){
            dp[i] = Math.max(dp[i - 2], dp[i - 3]) + sticker[i];
        }
        int answer = Math.max(dp[n - 2], dp[n - 3]);
        
        
        dp[0] = 0;
        dp[1] = sticker[1];
        dp[2] = sticker[2];
        
        for(int i = 3; i < n; i++){
            dp[i] = Math.max(dp[i - 2], dp[i - 3]) + sticker[i];
        }
        answer = Math.max(answer, Math.max(dp[n - 1], dp[n - 2]));

        return answer;
    }
}
