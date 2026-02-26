import java.util.*;
class Solution {
    public String solution(String number, int k) {
        char[] nums = number.toCharArray();
        Deque<Integer> deq = new ArrayDeque();
        deq.addFirst(nums[0] - '0');
        
        int idx = 1;
        while(k > 0 && idx < nums.length){
            while(k > 0 && idx < nums.length 
                  && deq.size() > 0 && nums[idx] - '0' > deq.getLast()){
                deq.removeLast();
                k--;
            }
            if(idx < nums.length) deq.addLast(nums[idx++] - '0');
        }

        StringBuilder sb = new StringBuilder();
        while(k > 0 && !deq.isEmpty()){
            deq.removeLast();
            k--;
        }
        
        while(!deq.isEmpty()) sb.append(deq.removeFirst());
        while(idx < nums.length) sb.append(nums[idx++] - '0');

        return sb.toString();
    }
}