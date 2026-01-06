import java.util.*;

class Solution {
    public long solution(int[] weights) {
        HashMap<Integer, Long> map = new HashMap();
        
        for(int w : weights){
            map.put(w, map.getOrDefault(w, 0l) + 1);
        }
        
        long cnt = 0;
        for(int w : map.keySet()){
            long wCnt = map.getOrDefault(w, 0l);
            
            if(wCnt == 0) continue;
            
            // 1:1
            cnt += (wCnt * (wCnt - 1l)) / 2;
            
            // 1:2
            cnt += wCnt * map.getOrDefault(w * 2, 0l);
            
            // 2:3
            if(w % 2 == 0){
                cnt += wCnt * map.getOrDefault((w / 2 * 3), 0l);
            }
            
            // 3:4
            if(w % 3 == 0){
                cnt += wCnt * map.getOrDefault((w / 3 * 4), 0l);
            }
        }
        
        return cnt;
    }
}