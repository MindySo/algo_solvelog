import java.util.*;
class Solution {
    static List<Integer>[] banable;
    static boolean[] visited;
    static Set<Integer> answer;
    public int solution(String[] user_id, String[] banned_id) {
        banable = new List[banned_id.length];
        
        for(int b = 0; b < banned_id.length; b++){
            banable[b] = new ArrayList();
            String ban = banned_id[b];
            int cnt = 0;
            
            nextUser:
            for(int u = 0; u < user_id.length; u++){
                String user = user_id[u];
                
                if(ban.length() != user.length()) continue;
                char[] banArr = ban.toCharArray();
                char[] userArr = user.toCharArray();

                for(int i = 0; i < banArr.length; i++){
                    if(banArr[i] == '*') continue;
                    if(banArr[i] != userArr[i]) continue nextUser;
                }
                banable[b].add(u);
            }
        }
        
        visited = new boolean[user_id.length];
        answer = new HashSet();
        
        DFS(0);
        
        return answer.size();
    }
    
    public void DFS(int idx){
        if(idx == banable.length) {
            Integer bans = 0;
            for(int i = 0; i < visited.length; i++){
                bans *= 10;
                if(visited[i]) bans += 1;
            }
            answer.add(bans);
            return;
        }
        
        for(int user : banable[idx]){
            if(visited[user]) continue;
            visited[user] = true;;
            DFS(idx + 1);
            visited[user] = false;
        }
    }
}