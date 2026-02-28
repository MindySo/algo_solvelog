import java.util.*;
class Solution {
    public int solution(String name) {
        char[] target = name.toCharArray();

        int answer = 0;
        int aLen = Integer.MAX_VALUE;
        
        for(int i = 0; i < target.length; i++){
            if(target[i] == 'A'){
                int s = i, e = i, l = 1;
                while(i + 1 < target.length && target[i + 1] == 'A'){
                    e = ++i;
                    l++;
                }
                aLen = Math.min(aLen, getALen(s, e, target.length));
            }else{
                if(target[i] <= 'N'){
                    answer += target[i] - 'A';
                }else{
                    answer += 26 - (target[i] - 'A');
                }
            }
        }

        return answer + Math.min(target.length - 1, aLen);
    }
    
    public int getALen(int start, int end, int length){
        if(start == 0) return length - end - 1;
        if(end == length - 1) return start - 1;
        
        int n = start - 1;
        int m = length - end - 1;
        return Math.min(n * 2 + m, m * 2 + n);
    }
}