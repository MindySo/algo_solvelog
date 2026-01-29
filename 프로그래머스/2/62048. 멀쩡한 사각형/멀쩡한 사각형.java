import java.util.*;
class Solution {
    public long solution(int w, int h) {
        long 최대공약수 = 1;
        long 분모 = w;
        long 분자 = h;
        
        int min = Math.min(w, h);
        for(int i = 2; i <= min; i++){
            while(분모 % i == 0 && 분자 % i == 0){
                분모 /= i;
                분자 /= i;
                최대공약수 *= i;
            }
        }
        
        long cnt = 0;
        long curr, next = 0;
        for(int x = 0; x < 분모; x++){
            curr = next;
            next = 분자 * (x + 1) / 분모;
            cnt += next - curr + 1;
        }
        cnt--;
        
        long answer = (long)w * (long)h - cnt * 최대공약수;
        
        return answer;
    }
}