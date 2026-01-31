import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        Map<String, String> map = new HashMap();
        String[][] newRecord = new String[record.length][2];
        
        StringTokenizer st;
        int idx = 0;
        int answerLength = 0;
        boolean leaved;
        for(String str : record){
            st = new StringTokenizer(str);
            leaved = false;
            
            String behav = st.nextToken();
            if(behav.charAt(0) == 'E'){
                behav = "들어왔습니다.";
                answerLength++;
            }else if(behav.charAt(0) == 'L'){
                behav = "나갔습니다.";
                answerLength++;
                leaved = true;
            }
            
            String uid = st.nextToken();
            
            if(!leaved){
                String name = st.nextToken();
                map.put(uid, name);
            }
            
            newRecord[idx][1] = behav;
            newRecord[idx++][0] = uid;
        }
        
        String[] answer = new String[answerLength];
        idx = 0;
        StringBuilder sb;
        for(String[] rec : newRecord){
            sb = new StringBuilder();
            sb.append(map.get(rec[0])).append("님이 ");
            if(rec[1].charAt(0) == 'C'){
                continue;
            }
            sb.append(rec[1]);
            answer[idx++] = sb.toString();
        }
        
        return answer;
    }
}