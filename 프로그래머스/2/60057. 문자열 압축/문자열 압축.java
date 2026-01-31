import java.util.*;
class Solution {
    public int solution(String s) {
        int min = s.length();
        int idx, length, zipped, 단위 = 1;
        String prev, curr;
        
        while(단위 <= s.length() / 2){
            curr = s.substring(0, 단위);
            idx = 단위;
            length = 단위;
            zipped = 0;
            
            while(idx + 단위 <= s.length()){
                prev = curr;
                curr = s.substring(idx, idx + 단위);
                if(prev.equals(curr)){
                    zipped = zipped == 0 ? 2 : zipped + 1;
                    if(zipped == 2 || zipped == 10 || zipped == 100 || zipped == 1000){
                        length++;
                    }
                }else{
                    length += 단위;
                    zipped = 0;
                }

                idx += 단위;
            }

            length += s.length() - idx;
            min = Math.min(min, length);
            단위++;
        }
        
        return min;
    }
}