import java.util.*;
class Solution {
    public long solution(String expression) {
        List<Long> nums = new ArrayList();
        Deque<Long> tempNums = new ArrayDeque();
        List<Character> cals = new ArrayList();
        Queue<Character> tempCals = new ArrayDeque();
        
        long num = 0;
        for(char c : expression.toCharArray()){
            if(c == '+' || c == '-' || c == '*'){
                nums.add(num);
                cals.add(c);
                num = 0;
            }else{
                num = num * 10 + (c - '0');
            }
        }
        nums.add(num);
        
        char[][] orders = {{'+', '-', '*'}, {'+', '*', '-'}, {'-', '+', '*'}, 
                         {'-', '*', '+'}, {'*', '+', '-'}, {'*', '-', '+'}};
        
        long max = 0;
        long result, prev, curr, next, calSize;
        char cal;
        
        for(char[] order : orders){
            for(long n : nums){
                tempNums.addLast(n);
            }

            for(char c : cals){
                tempCals.add(c);
            }
            
            for(char 연산자 : order){
                prev = tempNums.getFirst();
                tempNums.removeFirst();
                tempNums.addLast(prev);
                
                calSize = tempCals.size();
                for(int c = 0; c < calSize; c++){
                    curr = tempNums.getFirst();
                    tempNums.removeFirst();
                    cal = tempCals.poll();
                    
                    if(cal == 연산자){
                        prev = tempNums.getLast();
                        tempNums.removeLast();
                        curr = calculate(prev, curr, 연산자);
                        tempNums.addLast(curr);
                    }else{
                        tempNums.addLast(curr);
                        tempCals.add(cal);
                    }
                }
            }// 해당 연산자에 대한 계산
            
            result = tempNums.getLast();
            tempNums.removeLast();
            
            if(result < 0) result *= -1;
            max = Math.max(max, result);
        }// 해당 우선순위에 따른 계산
        
        return max;
    }
    
    public long calculate(long i, long j, char 연산자){
        if(연산자 == '+'){
            return i + j;
        }else if(연산자 == '-'){
            return i - j;
        }else{
            return i * j;
        }
    }
}