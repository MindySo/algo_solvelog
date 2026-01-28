import java.util.*;
class Solution {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> stk = new Stack();
        
        for(int i = 0; i < prices.length; i++){
            while(!stk.isEmpty() && prices[stk.peek()] > prices[i]){
                answer[stk.peek()] = i - stk.pop();
            }
            stk.push(i);
        }
        
        while(!stk.isEmpty()){
            answer[stk.peek()] = prices.length - stk.pop() - 1;
        }
        
        return answer;
    }
}