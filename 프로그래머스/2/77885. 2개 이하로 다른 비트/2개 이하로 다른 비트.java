import java.util.*;
class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        int idx = 0;
        
        nextNum:
        for(long num : numbers){
            String str = Long.toBinaryString(num);
            int len = str.length();
            
            if(str.charAt(len - 1) == '0') {
                answer[idx++] = num + 1;
                continue nextNum;
            }
            
            if(Long.bitCount(num) == len){
                answer[idx++] = num + 
                (long)Math.pow(2, len) - (long)Math.pow(2, len - 1);
                continue nextNum;
            }
            
            for(int i = 1; i < len; i++){
                if(str.charAt(len - (i + 1)) == '0'){
                    answer[idx++] = num + 
                        (long)Math.pow(2, i) - (long)Math.pow(2, i - 1);
                    continue nextNum;
                }
            }
        }
        return answer;
    }
}