import java.util.*;

class Solution {
    static String target;
    static char[] dict = new char[] {'X', 'A', 'E', 'I', 'O', 'U'};
    public int solution(String word) {
        target = word;
        while(target.length() < 5){
            target += "X";
        }
        
        int[] curr = new int[] {1, 0, 0, 0, 0};
        int idx, answer = 1;
        
        while(true){
            if(ifEqual(curr)) break;
            
            idx = 4;
            
            if(curr[idx] == 0){
                while(curr[idx - 1] == 0) idx--;
                curr[idx] = 1;
            }else if(curr[idx] == 5){
                while(curr[idx] == 5) idx--;
                curr[idx++]++;
                while(idx < 5) curr[idx++] = 0;
            }else{
                curr[idx]++;
            }
            answer++;
        }
        
        return answer;
    }
    
    public boolean ifEqual(int[] arr){
        if(dict[arr[0]] == target.charAt(0)
          && dict[arr[1]] == target.charAt(1)
          && dict[arr[2]] == target.charAt(2)
          && dict[arr[3]] == target.charAt(3)
          && dict[arr[4]] == target.charAt(4)){
            return true;
        }
        return false;
    }
}