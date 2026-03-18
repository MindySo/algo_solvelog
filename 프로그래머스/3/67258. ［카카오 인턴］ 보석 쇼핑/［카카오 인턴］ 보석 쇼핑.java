import java.util.*;
class Solution {
    static String[] gemArr;
    static Map<String, Integer> map;
    static Deque<Integer> deq;
    static int N, min;
    static int[] answer;
    public int[] solution(String[] gems) {
        Set<String> set = new HashSet();
        for(String g : gems) set.add(g);
        N = set.size();
        
        gemArr = gems;
        map = new HashMap();
        deq = new ArrayDeque();
        min = Integer.MAX_VALUE;
        answer = new int[2];
        int idx = 0;
        
        while(idx < gems.length){
            if(map.size() == N){
               remove();
            } else{
                deq.addLast(idx);
                String gem = gemArr[idx++];
                map.put(gem, map.getOrDefault(gem, 0) + 1);
            }
            check();
        }
        
        while(map.size() == N){
            remove();
            check();
        }
        
        return answer;
    }
    
    public void remove(){
        String gem = gemArr[deq.removeFirst()];
        map.put(gem, map.get(gem) - 1);
        if(map.get(gem) == 0) map.remove(gem);
    }
    
    public void check(){
        if(map.size() == N && deq.size() < min){
            min = deq.size();
            answer[0] = deq.getFirst() + 1;
            answer[1] = deq.getLast() + 1;
        }
    }
    
}