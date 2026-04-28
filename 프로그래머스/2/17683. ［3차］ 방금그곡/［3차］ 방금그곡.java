import java.util.*;
class Solution {
    public String solution(String m, String[] musicinfos) {
        String answer = "(None)";
        int maxTime = 0;
        
        m = convert(m);
        
        for(String infos : musicinfos){
            String[] info = infos.split(",");
            int start = ((info[0].charAt(0) - '0') * 10 + (info[0].charAt(1) - '0')) * 60
                + ((info[0].charAt(3) - '0') * 10 + (info[0].charAt(4) - '0'));
            int end = ((info[1].charAt(0) - '0') * 10 + (info[1].charAt(1) - '0')) * 60
                + ((info[1].charAt(3) - '0') * 10 + (info[1].charAt(4) - '0'));
            int duration = end - start;
            
            String title = info[2];
            String melody = convert(info[3]);
            
            Queue<Integer> que = new ArrayDeque();
            for(int i = 0; i < melody.length(); i++)
                if(melody.charAt(i) == m.charAt(0)) que.add(i);
            
            findSame:
            while(!que.isEmpty()){
                int time = que.poll();
                int mIdx = 0;
                while(time <= duration){
                    char melodyM = melody.charAt(time++ % melody.length());
                    char musicM = m.charAt(mIdx);

                    if(melodyM == musicM) {
                        if(++mIdx == m.length()){
                            if(duration > maxTime){
                                answer = title;
                                maxTime = duration;
                            }
                            break findSame;
                        }
                    } else mIdx = 0;
                }
            }
        }
        
        return answer;
    }
    
    public String convert(String original){
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < original.length(); i++){
            char c = original.charAt(i);
            if(i + 1 < original.length() && original.charAt(i + 1) == '#') {
                c += 32;
                i++;
            }
            sb.append(c);
        }
        return sb.toString();
    }
}