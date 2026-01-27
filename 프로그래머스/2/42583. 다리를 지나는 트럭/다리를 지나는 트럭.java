import java.util.*;
class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        Deque<Integer> deque = new ArrayDeque();
        int sum = 0;
        int idx = 0;
        int time = 0;

        for(int i = 0; i < bridge_length; i++){
            deque.addFirst(0);
        }
        
        while(true){
            if(idx >= truck_weights.length && sum == 0) break;
            
            sum -= deque.getLast();
            deque.removeLast();
            time++;
            
            if(idx < truck_weights.length && sum + truck_weights[idx] <= weight){
                deque.addFirst(truck_weights[idx]);
                sum += truck_weights[idx++];
            }else{
                deque.addFirst(0);
            }
        }
        
        return time;
    }
}