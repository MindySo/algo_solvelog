import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        Map<String, Integer> earned = new HashMap();
        Map<String, String> parent = new HashMap();
        
        for(int i = 0; i < enroll.length; i++){
            earned.put(enroll[i], 0);
        }
        earned.put("-", 0);
        
        for(int i = 0; i < enroll.length; i++){
            parent.put(enroll[i], referral[i]);
        }
        
        for(int i = 0; i < seller.length; i++){
            int total = amount[i] * 100;
            String name = seller[i];
            while(total / 10 > 0){
                earned.put(name, earned.get(name) + (total - total / 10));
                total = total / 10;
                if(name.equals("-")) break;
                name = parent.get(name);
            }
            earned.put(name, earned.get(name) + total);
        }
        
        int[] answer = new int[enroll.length];
        for(int i = 0; i < enroll.length; i++) answer[i] = earned.get(enroll[i]);
        return answer;
    }
}