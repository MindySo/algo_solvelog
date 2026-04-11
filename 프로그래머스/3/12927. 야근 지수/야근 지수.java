import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                return o2 - o1;
            }
        });
        
        for(int work : works) pq.add(work);
        while(n-- > 0){
            int curr = pq.poll();
            if(curr == 0) break;
            pq.add(curr - 1);
        }
        
        long answer = 0;
        while(!pq.isEmpty()) answer += Math.pow(pq.poll(), 2);
        
        return answer;
    }
}