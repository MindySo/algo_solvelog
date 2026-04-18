class Solution {
    public long solution(long n) {
        long answer = -1;
        
        for(int i = 1; i < Integer.MAX_VALUE; i++){
            if(n == (long)i * (long)i){
                answer = (long)(i + 1) * (long)(i + 1);
                break;
            }
        }
        return answer;
    }
}