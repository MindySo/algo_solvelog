import java.util.*;
class Solution {
    public int solution(int[] citations) {
        int[] citated = new int[10001];
        
        for(int c : citations){
            citated[c]++;
        }
        
        int totalCnt = 0, answer = 0, size = citations.length;
        for(int i = 0; i < citated.length; i++){
            if(size - totalCnt >= i && totalCnt <= i) answer = i;
            totalCnt += citated[i];
        }
        return answer;
    }
}