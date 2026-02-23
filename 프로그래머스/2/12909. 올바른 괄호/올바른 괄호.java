import java.util.*;
class Solution {
    boolean solution(String s) {
        Stack<Character> stk = new Stack();
        char[] chars = s.toCharArray();
        stk.push(chars[0]);
        
        for(int i = 1; i < chars.length; i++){
            if(chars[i] == ')'){
                if(!stk.isEmpty() && stk.peek() == '(') {
                    stk.pop();
                }else{
                    return false;
                }
            }else{
                stk.push(chars[i]);
            }
        }
        
        if(stk.isEmpty()) return true;
        
        return false;
    }
}