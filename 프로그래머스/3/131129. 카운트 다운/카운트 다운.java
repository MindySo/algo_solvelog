class Solution {
    public int[] solution(int target) {
        int len = target + 1 > 61 ? target + 1 : 61;
        int[][] dp = new int[len][2];
        
        for(int i = 1; i <= 20; i++){
            dp[i][0] = 1;
            dp[i][1] = 1;
        }
        
        for(int i = 21; i <= 40; i++){
            if(i % 2 == 0 || i % 3 == 0){
                dp[i][0] = 1;
                dp[i][1] = 0;
            }else{
                dp[i][0] = 2;
                dp[i][1] = 2;
            }
        }
        
        for(int i = 41; i < 50; i++){
            if(i % 3 == 0){
                dp[i][0] = 1;
                dp[i][1] = 0;
            }else{
                dp[i][0] = dp[40][0] + 1;
                dp[i][1] = dp[40][1] + 1;
            }
        }
        
        dp[50][0] = 1;
        dp[50][1] = 1;
        
        for(int i = 51; i <= 60; i++){
            if(i % 3 == 0){
                dp[i][0] = 1;
                dp[i][1] = 0;
            }else{
                dp[i][0] = dp[50][0] + 1;
                dp[i][1] = dp[50][1] + 1;
            }
        }
        
        if(target <= 60) return dp[target];
        
        for(int i = 61; i <= target; i++){
            int cnt = Integer.MAX_VALUE;
            int single = Integer.MAX_VALUE;
            
            for(int j = 1; j <= 60; j++){
                int newCnt = dp[j][0] + dp[i - j][0];
                int newSingle = dp[j][1] + dp[i - j][1];
                
                if(newCnt < cnt){
                    cnt = newCnt;
                    single = newSingle;
                }else if(newCnt == cnt && newSingle > single) single = newSingle;
            }
            
            dp[i][0] = cnt;
            dp[i][1] = single;
        }
        
        return dp[target];
    }
}