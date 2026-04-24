import java.util.*;
class Solution {
    public String solution(String s) {
        char[] arr = new char[s.length()];
        for(int i = 0; i < s.length(); i++){
            arr[i] = s.charAt(i);
        }
        Arrays.sort(arr);
        
        StringBuilder sb = new StringBuilder();
        for(int i = s.length() - 1; i >= 0; i--) sb.append(arr[i]);

        return sb.toString();
    }
}