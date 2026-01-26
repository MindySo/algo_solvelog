import java.util.*;
class Solution {
    public int solution(String[][] clothes) {
        Map<String, List<String>> map = new HashMap();
        for(String[] item : clothes){
            if(!map.keySet().contains(item[1])) map.put(item[1], new ArrayList());
            map.get(item[1]).add(item[0]);
        }
        
        int cases = 1;
        for(List<String> list : map.values()){
            cases *= (list.size() + 1);
        }

        return cases - 1;
    }
}