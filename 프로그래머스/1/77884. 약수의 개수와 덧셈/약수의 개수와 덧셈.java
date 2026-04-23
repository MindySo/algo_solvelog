class Solution {
    public int solution(int left, int right) {
        int[] cnt = new int[right + 1];
        for(int i = 1; i <= right; i++){
            int num = i;
            while(num <= right){
                cnt[num]++;
                num += i;
            }
        }
        
        int answer = 0;
        for(int i = left; i <= right; i++){
            if(cnt[i]% 2 == 0) answer += i;
            else answer -= i;
        }
        
        return answer;
    }
}