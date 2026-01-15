import java.util.*;

class Solution {
    public int solution(int n, int k) {
        Stack<Integer> stk = new Stack();
        
        while(n > 0){
            stk.push(n % k);
            n /= k;
        }

        int answer = 0;
        long num = 0;
        long curr;
        while(!stk.isEmpty()){
            curr = stk.pop();
            if(curr != 0){
                num = num * 10 + curr;
            }else{
                answer += isPrime(num);
                num = 0;
            }
        }
        
        answer += isPrime(num);
        
        return answer;
    }
    
    public int isPrime(long num){
        if(num == 0 || num == 1) return 0;
        
        long sqrt = (long)Math.sqrt(num);
        for(int i = 2; i <= sqrt; i++){
            if(num % i == 0) return 0;
        }
        return 1;
    }
}