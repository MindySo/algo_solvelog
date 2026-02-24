import java.util.*;
class Solution {
    public int solution(int[] people, int limit) {
        Arrays.sort(people);
        
        int cnt = 0;
        Deque<Integer> deque = new ArrayDeque();
        for(int i = people.length - 1; i >= 0; i--){
            if(!deque.isEmpty() && deque.getLast() >= people[i]){
                deque.removeLast();
                cnt++;
            }else{
                deque.addLast(limit - people[i]);
            }
        }
        return cnt + deque.size();
    }
}