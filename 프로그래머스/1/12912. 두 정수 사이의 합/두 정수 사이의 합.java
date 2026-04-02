import java.util.*;
class Solution {
    public long solution(int a, int b) {
        long gap = Math.abs(a - b);
        long answer = (a + b) * ((gap + 1) / 2);
        if(gap % 2 == 0) answer += (a + b) / 2;
        return answer;
    }
}