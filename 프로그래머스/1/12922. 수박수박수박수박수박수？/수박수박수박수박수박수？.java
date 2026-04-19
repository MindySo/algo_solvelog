import java.util.*;
class Solution {
    public String solution(int n) {
        String subak = "수박";
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n / 2; i++) sb.append(subak);
        if(n % 2 == 1) sb.append("수");
        
        return sb.toString();
    }
}