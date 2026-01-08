import java.util.*;

class Solution {
    public long solution(int k, int d) {
        long cnt = 0;
        double power = Math.pow(d, 2);
        long sqrt = (long)Math.sqrt(power / 2);
        
        for(long i = 0; i * k <= sqrt; i++){
            long max = (long)Math.sqrt(power - Math.pow(i * k, 2));
            cnt += max / k - i;
        }
        
        cnt = cnt * 2 + (sqrt / k + 1);
        return cnt;
    }
}