import java.util.*;

class Solution {
    static String str;
    static Queue<Integer> que;
    static Deque<Integer> deque;
    
    public int solution(String s) {
        str = s;
        que = new ArrayDeque();
        deque = new ArrayDeque();
        int answer = 0;

        for(int i = 0; i < s.length(); i++){
            que.add(i);
            arrange(i);
        }
        
        for(int i = 0; i < s.length(); i++){
            if(deque.isEmpty()) answer++;
            arrange(que.poll());
        }
        
        return answer;
    }
    
    public boolean ifClosed(int i){
        if(deque.isEmpty()) return false;
        char c = str.charAt(i);
        char first = str.charAt(deque.getFirst());
        if(first == '{' && c == '}') return true;
        if(first == '[' && c == ']') return true;
        if(first == '(' && c == ')') return true;
        return false;
    }
    
    public void arrange(int i){
        if(!deque.isEmpty() && deque.getLast() == i) deque.removeLast();
        if(ifClosed(i)){
            deque.removeFirst();
        }else{
            deque.addFirst(i);
        }
    }
}