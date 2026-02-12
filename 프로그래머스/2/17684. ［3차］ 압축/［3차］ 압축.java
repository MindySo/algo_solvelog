import java.util.*;
class Solution {
    public int[] solution(String msg) {
        Map<String, Integer> map = new HashMap();
        for(int i = 'A'; i <= 'Z'; i++){
            String str = "" + (char)i;
            map.put(str, i - 'A' + 1);
        }
        
        List<Integer> answer = new ArrayList();
        int idx = 0;
        String str = "";
        while(true){
            str += msg.charAt(idx++);
            if(idx >= msg.length()) break;
            if(map.keySet().contains(str + msg.charAt(idx))) continue;
            answer.add(map.get(str));
            map.put(str + msg.charAt(idx), map.size() + 1);
            str = "";
        }
        answer.add(map.get(str));

        return answer.stream().mapToInt(i -> i).toArray();
    }
}