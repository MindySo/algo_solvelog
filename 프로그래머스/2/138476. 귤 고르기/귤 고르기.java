import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        HashMap<Integer, Integer> map = new HashMap();
        for(int t : tangerine){
            map.put(t, map.getOrDefault(t, 0) + 1);
        }
        
        ArrayList<Integer> amount = new ArrayList(map.values());
        Collections.sort(amount, Collections.reverseOrder());
        
        int types = 0;
        int idx = 0;
        while(k > 0){
            k -= amount.get(idx++);
            types++;
        }
        
        return types;
    }
}