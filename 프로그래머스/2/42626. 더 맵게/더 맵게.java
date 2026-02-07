import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> pq = new PriorityQueue(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                return o1 - o2;
            }
        });
        
        for(int s : scoville){
            pq.add(s);
        }
        
        int food1, food2, cnt = 0;
        while(pq.size() >= 2){
            food1 = pq.poll();
            if(food1 >= K) return cnt;
            
            food2 = pq.poll();
            pq.add(food1 + food2 * 2);
            cnt++;
        }

        if(pq.poll() < K) cnt = -1;
        return cnt;
    }
}