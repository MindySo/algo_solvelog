import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap();
        String[][] newRecord = new String[record.length][2];
        
        int idx = 0;
        int answerLength = 0;
        boolean leaved;
        for(String str : record){
            String[] rec = str.split(" ");
            
            if(rec[0].charAt(0) == 'E'){
                rec[0] = "들어왔습니다.";
                answerLength++;
            }else if(rec[0].charAt(0) == 'L'){
                rec[0] = "나갔습니다.";
                answerLength++;
            }
            
            if(rec.length != 2){
                map.put(rec[1], rec[2]);
            }
            
            newRecord[idx][0] = rec[1];
            newRecord[idx++][1] = rec[0];
        }
        
        String[] answer = new String[answerLength];
        StringBuilder sb;
        idx = 0;
        for(String[] rec : newRecord){
            if(rec[1].charAt(0) == 'C'){
                continue;
            }
            sb = new StringBuilder();
            sb.append(map.get(rec[0])).append("님이 ").append(rec[1]);
            answer[idx++] = sb.toString();
        }
        
        return answer;
    }
}
