import java.util.*;
class Solution {
    public int solution(int[] order) {
        int num = 1;
        int idx = 0;
        int answer = 0;
        Stack<Integer> stk = new Stack();
        
        while(idx < order.length){
            if(num != order[idx]){
                stk.push(num++);
            }else{
                answer++;
                num++;
                idx++;
                
                while(!stk.isEmpty()){
                    if(stk.peek() == order[idx]){
                        stk.pop();
                        answer++;
                        idx++;
                    }else if(stk.peek() > order[idx]){
                        return answer;
                    }else{
                        break;
                    }
                }
            }
        }
        
        return answer;
    }
}