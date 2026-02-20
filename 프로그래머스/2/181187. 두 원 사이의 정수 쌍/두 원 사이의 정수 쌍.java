import java.util.*;

class Solution {
    public long solution(int r1, int r2) {
        long cnt = 0;
        long l = 1;
        while(l <= r2){
            long h1 = (long)Math.ceil(Math.sqrt(Math.pow(r1, 2) - Math.pow(l, 2)));
            long h2 = (long)Math.floor(Math.sqrt(Math.pow(r2, 2) - Math.pow(l, 2)));
            cnt += (h2 - h1 + 1);
            l++;
        }
        
        return cnt * 4;
    }
}