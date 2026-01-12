import java.util.*;

class Solution {
    static Queue<Integer> que1;
    static Queue<Integer> que2;
    static int max, limit, inserted;
    static long sum1, target;
    
    public int solution(int[] queue1, int[] queue2) {
        init(queue1, queue2);
        
        if(target % 2 != 0) return -1;
        target /= 2;
        
        while(insert());
        
        return inserted;
    }
    
    public void init(int[] queue1, int[] queue2){
        que1 = new ArrayDeque();
        que2 = new ArrayDeque();
        limit = queue1.length * 4;
        max = inserted = 0;
        sum1 = target = 0l;
        
        for(int num : queue1){
            que1.add(num);
            sum1 += num;
            max = Math.max(max, num);
        }

        target += sum1;
        
        for(int num : queue2){
            que2.add(num);
            target += num;
            max = Math.max(max, num);
        }
        
    } // init
    
    public boolean insert(){
        if(sum1 == target) return false;
        
        if(inserted >= limit){
            inserted = -1;
            return false;
        }
        
        if(!que1.isEmpty() && sum1 > target){
            int num = que1.poll();
            que2.add(num);
            sum1 -= num;
        }else if(!que2.isEmpty() && sum1 < target){
            int num = que2.poll();
            que1.add(num);
            sum1 += num;
        }
        
        inserted++;
        return true;
    } // insert
}