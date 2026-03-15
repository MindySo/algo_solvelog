import java.util.*;
class Solution {
    public int solution(String s) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        
        int answer = 1;
        for(int i = 0; i < n; i++){
            int idx = 0;
            while(i - idx >= 0 && i + idx < n){
                if(arr[i - idx] != arr[i + idx]) break;
                idx++;
            }
            answer = Math.max(answer, idx * 2 - 1);
            
            idx = 0;
            while(i - idx >= 0 && i + 1 + idx < n){
                if(arr[i - idx] != arr[i + 1 + idx]) break;
                idx++;
            }
            answer = Math.max(answer, idx * 2);
        }
        
        return answer;
    }
}