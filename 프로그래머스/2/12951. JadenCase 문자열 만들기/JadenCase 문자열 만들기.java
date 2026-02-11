import java.util.*;

class Solution {
    public String solution(String s) {
        String answer = "";
        boolean spaced = true;
        for(char c : s.toCharArray()){
            if(spaced){
                spaced = false;
                if(c >= 'a' && c <= 'z') c -= 32;
            }else{
                if(c >= 'A' && c <= 'Z') c += 32;
            }
            answer += c;
            
            if(c == ' ') spaced = true;
        }
        
        return answer;
    }
}