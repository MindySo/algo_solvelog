import java.util.*;
class Solution {
    public int solution(int storey) {
        int minus = storey % 10;
        int plus = 10 - (storey % 10);
        storey /= 10;
        
        int curr, mm, mp, pm, pp;
        int newMinus = minus;
        int newPlus = plus;
        
        while(storey > 0){
            curr = storey % 10;
            storey /= 10;
            minus = newMinus;
            plus = newPlus;
            
            mm = curr + minus;
            mp = (curr + 1) + plus;
            newMinus = Math.min(mm, mp);
            
            pm = (10 - curr) + minus;
            pp = (10 - (curr + 1)) + plus;
            newPlus = Math.min(pm, pp);
            
        }
        
        int answer = Math.min(newMinus, newPlus + 1);
        return answer;
    }
}