import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        Map<Integer, Integer> map = new HashMap();
        for(String str : timetable){
            String[] arr = str.split(":");
            int time = Integer.parseInt(arr[0]) * 60 + Integer.parseInt(arr[1]);
            map.put(time, map.getOrDefault(time, 0) + 1);
        }
        
        int lastTime = 540 + t * (n - 1);
        int limit = n * m;
        int waiting = 0, took = 0, answer = lastTime;
        
        for(int time = 1; time <= lastTime; time++){
            if(waiting + map.getOrDefault(time, 0) + 1 > limit) {
                answer = time - 1;
                break;
            }
            
            waiting += map.getOrDefault(time, 0);
            
            if(time >= 540 && time % t == 0) {
                waiting = waiting - m < 0 ? 0 : waiting - m;
                limit -= m;
            }
        }
        
        String answerStr = "";
        answerStr += answer / 60 < 10 ? "0" + answer / 60 : answer / 60;
        answerStr += ":";
        answerStr += answer % 60 < 10 ? "0" + answer % 60 : answer % 60;
        
        return answerStr;
    }
}