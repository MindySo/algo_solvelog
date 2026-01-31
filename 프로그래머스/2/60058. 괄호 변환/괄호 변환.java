import java.util.*;

class Solution {
    static String origin;
    public String solution(String p) {
        origin = p;

        return process("", p);
    }
    
    public String process(String str, String p){
        if(p.length() == 0) return str;
        
        int idx = split(p);
        String u = p.substring(0, idx + 1);
        String v = p.substring(idx + 1, p.length());
        
        if(correct(u)){
            return process(str + u, v);
        }else{
            String newStr = "(" + process("", v) + ")";
            for(int i = 1; i < u.length() - 1; i++){
                if(u.charAt(i) == '('){
                    newStr += ")";
                }else{
                    newStr += "(";
                }
            }
            
            return str + newStr;
        }
    }
    
    public int split(String w){
        int open = 0;
        int close = 0;
        int idx = 0;
        String u = "";
        while(idx < w.length()){
            if(w.charAt(idx) == '('){
                open++;
                u += w.charAt(idx);
            }else{
                close++;
                u += w.charAt(idx);
            }
            
            if(open == close) break;
            idx++;
        }
        
        return idx;
    } 
    
    public boolean correct(String u){
        Stack<Character> stk = new Stack();
        for(int i = 0; i < u.length(); i++){
            if(u.charAt(i) == ')'){
                if(!stk.isEmpty() && stk.peek() == '('){
                    stk.pop();
                }else{
                    stk.push(')');
                }
            }else{
                stk.push('(');
            }
        }
        
        if(stk.isEmpty()) return true;
        
        return false;
    }
}