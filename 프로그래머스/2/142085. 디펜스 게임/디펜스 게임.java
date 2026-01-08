import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        PriorityQueue<Integer> pq = new PriorityQueue(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                return o1 - o2;
            }
        });
        
        int round = k <= enemy.length ? k : enemy.length;
        for(int e : enemy){
            pq.add(e);
            if(pq.size() > k){
                if(pq.peek() <= n){
                    n -= pq.poll();
                    round++;
                }else{
                    break;
                }
            }
        }
        return round;
    }
}