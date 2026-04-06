import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        int idx = 0;
        
        Deque<Character> deq = new ArrayDeque();
        for(String str : s){
            StringBuilder originSb = new StringBuilder();
            StringBuilder valueSb = new StringBuilder();
            StringBuilder lastSb = new StringBuilder();
            
            deq.clear();
            for(int i = 0; i < str.length(); i++){
                if(str.charAt(i) == '1') {
                    deq.addLast('1');
                }else{
                    if(deq.size() < 2){
                        deq.addLast('0');
                        continue;
                    }
                    
                    char last = deq.removeLast();
                    if(last == '1' && deq.getLast() == '1'){
                        deq.removeLast();
                        valueSb.append("110");
                    }else{
                        deq.addLast(last);
                        deq.addLast('0');
                    }
                }                
            }
            
            while(!deq.isEmpty()) {
                if(deq.getLast() == '1') lastSb.append(deq.removeLast());
                else break;
            }
            
            while(!deq.isEmpty()) originSb.append(deq.removeFirst());
            originSb.append(valueSb.toString());
            originSb.append(lastSb.toString());
            
            answer[idx++] = originSb.toString();
        }
        
        return answer;
    }
}