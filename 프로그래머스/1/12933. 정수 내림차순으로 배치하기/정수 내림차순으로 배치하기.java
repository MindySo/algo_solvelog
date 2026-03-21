import java.util.*;
class Solution {
    public long solution(long n) {
        PriorityQueue<Integer> pq = new PriorityQueue(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                return o2 - o1;
            }
        });
        while(n > 0){
            pq.add((int)(n % 10));
            n /= 10;
        }
        Long answer = 0l;
        while(!pq.isEmpty()){
            answer = answer * 10 + pq.poll();
        }
        
        return answer;
    }
}