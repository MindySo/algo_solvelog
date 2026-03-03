class Solution {
    public int solution(int n, int[] tops) {
        int[][] dp = new int[n][2];
        
        dp[0][0] = 3;
        dp[0][1] = 4;
        dp[1][0] = tops[0] == 0 ? 8 : 11;
        dp[1][1] = tops[0] == 0 ? 11 : 15;
        
        for(int i = 2; i < n; i++){
            if(tops[i - 1] == 0){
                dp[i][0] = ( dp[i - 1][0] * 3 - dp[i - 2][tops[i - 2]] + 10007) % 10007;
                dp[i][1] = ( dp[i - 1][0] * 3 + (dp[i - 1][0] - dp[i - 2][tops[i - 2]]) + 10007 ) % 10007;
            }else{
                dp[i][0] = ( dp[i - 1][1] * 3 - dp[i - 2][tops[i - 2]] + 10007 ) % 10007;
                dp[i][1] = ( dp[i - 1][1] * 3 + dp[i - 1][0] ) % 10007;
            }
        }
        
        return dp[n - 1][tops[n - 1]];
    }
}