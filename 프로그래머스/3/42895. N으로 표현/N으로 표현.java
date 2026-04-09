import java.util.*;
class Solution {
    public int solution(int N, int number) {
        int size = N;
        while(size < number) size = size * 10 + N;
        size = size * 10 + N;
        int len = (size + "").length();
        
        int[] nums = new int[len + 1];
        int k = N, l = 0;
        while(++l <= len){
            nums[l] = k;
            k = k * 10 + N;
        }
        
        int[] dp = new int[size + 1];
        Arrays.fill(dp, 10);
        dp[0] = 0;
        dp[1] = 2;
        
        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
            public int compare(int[] o1, int[] o2){
                return (o1[1] + o1[2]) - (o2[1] + o2[2]);
            }
        });
        
        for(int i = 1; i < nums.length; i++) dp[nums[i]] = i;
        for(int i = 1; i < nums.length; i++) pq.add(new int[] {nums[i], nums[i], 0});
        pq.add(new int[] {1, 1, 0});
        
        while(!pq.isEmpty()){
            int[] temp = pq.poll();
            int curr = temp[0];
            int val = dp[temp[1]] + dp[temp[2]];
            
            if(!(curr == 1 || curr == N || curr == 11 * N || curr == 111 * N 
                 || curr == 1111 * N || curr == 11111 * N) && dp[curr] <= val) continue;
            dp[curr] = val;
            
            if(dp[curr] + dp[1] < dp[curr - 1]){
                pq.add(new int[] {curr - 1, curr, 1});
            }
            
            if(curr + 1 <= size && dp[curr] + dp[1] < dp[curr + 1]){
                pq.add(new int[] {curr + 1, curr, 1});
            }
            
            for(int i = 1; i < nums.length; i++){
                int newNum = curr * nums[i];
                if(newNum > 0 && newNum <= size && dp[curr] + dp[nums[i]] < dp[newNum]) {
                    pq.add(new int[] {newNum, curr, nums[i]});
                }
                
                newNum = curr / nums[i];
                if(newNum > 0 && dp[curr] + dp[nums[i]] < dp[newNum]) {
                    pq.add(new int[] {newNum, curr, nums[i]});
                }
                
                newNum = curr + nums[i];
                if(newNum <= size && dp[curr] + dp[nums[i]] < dp[newNum]) {
                    pq.add(new int[] {newNum, curr, nums[i]});
                }
                
                newNum = curr - nums[i];
                if(newNum > 0 && dp[curr] + dp[nums[i]] < dp[newNum]) {
                    pq.add(new int[] {newNum, curr, nums[i]});
                }
            }
        }
        
        return dp[number] > 8 ? -1 : dp[number];
    }
}