import java.util.*;
class Solution {
    public long solution(int[] sequence) {
        long max = Long.MIN_VALUE;
        
        long num1, num2, sum1 = 0, sum2 = 0;
        int pulse1 = 1, pulse2 = -1;
        for(int n : sequence){
            num1 = n * pulse1;
            num2 = n * pulse2;
            
            sum1 = Math.max(sum1 + num1, num1);
            sum2 = Math.max(sum2 + num2, num2);

            max = Math.max(max, sum1);
            max = Math.max(max, sum2);
            
            pulse1 *= -1;
            pulse2 *= -1;
        }
        
        return max;
    }
}