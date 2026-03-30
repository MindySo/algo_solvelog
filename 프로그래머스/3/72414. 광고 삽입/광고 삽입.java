import java.util.*;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        long[] times = new long[convert(play_time) + 1];
    
        for(String str : logs){
            String[] t = str.split("-");
            times[convert(t[0])]++;
            times[convert(t[1])]--;
        }
        
        for(int i = 1; i < times.length; i++){
            times[i] += times[i - 1];
        }
        
        for(int i = 1; i < times.length; i++){
            times[i] += times[i - 1];
        }
        
        int adLength = convert(adv_time);
        long max = times[adLength - 1];
        int answer = 0;
        for(int i = 1; i < times.length - adLength + 1; i++){
            long sum = times[i + adLength - 1] - times[i - 1];
            if(sum > max){
                max = sum;
                answer = i;
            }
        }
        
        int h = answer / 3600;
        int m = answer % 3600 / 60;
        int s = answer % 3600 % 60;
        
        return (h < 10 ? "0" : "") + h + ":" + 
                (m < 10 ? "0" : "") + m + ":" +
                (s < 10 ? "0" : "") + s;
    }
    
    public int convert(String str){
        int h = (str.charAt(0) - '0') * 10 + (str.charAt(1) - '0');
        int m = (str.charAt(3) - '0') * 10 + (str.charAt(4) - '0');
        int s = (str.charAt(6) - '0') * 10 + (str.charAt(7) - '0');
        
        return h * 60 * 60 + m * 60 + s;
    }
    
    public String revert(int time){
        int h = time / 3600;
        int m = time % 3600 / 60;
        int s = time % 3600 % 60;
        
        return (h < 10 ? "0" : "") + h + ":" + 
                (m < 10 ? "0" : "") + m + ":" +
                (s < 10 ? "0" : "") + s;
    }
}