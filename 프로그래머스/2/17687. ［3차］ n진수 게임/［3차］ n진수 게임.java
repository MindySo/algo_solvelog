import java.util.*;

class Solution {
    static Stack<Integer> stk;
    static int N;
    public String solution(int n, int t, int m, int p) {
        stk = new Stack();
        N = n;
        
        StringBuilder sb = new StringBuilder();
        int num, number = 1, order = 1;
        stk.push(0);
        
        while(sb.length() < t){
            if(stk.isEmpty()) getNum(number++);
            
            num = stk.pop();
            if(order++ == p) {
                if(num < 10){
                    sb.append(num);
                }else{
                    char c = 'A';
                    c += num - 10;
                    sb.append(c);
                }
                p += m;
            }
        }
        return sb.toString();
    }
    
    public void getNum(int number){
        while(number > 0){
            stk.push(number % N);
            number /= N;
        }
    }
}