import java.util.*;

class Solution {
    static int INF = 300_000;
    public static int solution(int[] diffs, int[] times, long limit) {
        int start = 1, end = INF, mid, retry, min = INF;
        long spentTime;
        
        while(start <= end){
            mid = (start + end) / 2;
            spentTime = times[0];
            
            for(int i = 1 ; i < times.length ; i++){
                retry = diffs[i] - mid;
                if(retry > 0){
                    spentTime += (times[i-1] + times[i]) * retry;
                }
                spentTime += times[i];
            }
            
            if(spentTime > limit){
                start = mid + 1;
            }else{
                end = mid - 1;
                min = mid;
            }
        }
        
        return min;
   }
}