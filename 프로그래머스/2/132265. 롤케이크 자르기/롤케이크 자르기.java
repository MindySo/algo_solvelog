import java.util.*;
class Solution {
    public int solution(int[] topping) {
        HashMap<Integer, Integer> mapA = new HashMap();
        HashMap<Integer, Integer> mapB = new HashMap();
        
        for(int t : topping){
            mapA.put(t, mapA.getOrDefault(t, 0) + 1);
        }
        
        int cnt = 0;
        
        for(int t : topping){
            if(mapA.get(t) == 1){
                mapA.remove(t);
            }else{
                mapA.put(t, mapA.get(t) - 1);
            }
            mapB.put(t, mapB.getOrDefault(t, 0) + 1);
            
            if(mapA.keySet().size() == mapB.keySet().size()){
                cnt++;
            }
        }
        
        return cnt;
    }
}