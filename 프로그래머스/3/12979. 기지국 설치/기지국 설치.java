import java.util.*;
class Solution {
    public int solution(int n, int[] stations, int w) {
        List<Integer> noSignal = new ArrayList();
        int last = 0;

        for(int station : stations){
            int start = station - w;
            int blank = start - last - 1;
            if(blank > 0) noSignal.add(blank);
            last = station + w;
        }
        
        if(n - last > 0) noSignal.add(n - last);
        
        int answer = 0;
        int sig = w * 2 + 1;
        
        for(int blank : noSignal){
            answer += blank / sig;
            if(blank % sig > 0) answer++;
        }

        return answer;
    }
}