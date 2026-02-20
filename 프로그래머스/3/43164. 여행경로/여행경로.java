import java.util.*;
class Solution {
    static int N;
    static Map<String, List<String>> map;
    static Map<String, Integer> many;
    static Map<String, Integer> visited;
    static List<String> answer;
    
    public String[] solution(String[][] tickets) {
        N = tickets.length + 1;
        map = new HashMap();
        many = new HashMap();
        visited = new HashMap();
        answer = new ArrayList();
        
        many.put("root ICN", 1);
        visited.put("root ICN", 0);
        
        for(String[] ticket : tickets){
            if(!map.keySet().contains(ticket[0])) map.put(ticket[0], new ArrayList());
            map.get(ticket[0]).add(ticket[1]);
            String t = ticket[0] + " " + ticket[1];
            many.put(t, many.getOrDefault(t, 0) + 1);
            visited.put(t, 0);
        }
        
        for(String city : map.keySet()){
            Collections.sort(map.get(city), new Comparator<String>(){
                public int compare(String o1, String o2){
                    return o1.compareTo(o2);
                }
            });
        }
        
        DFS("root ICN");
        String[] answerArr = new String[answer.size()];
        int idx = 0;
        for(String city : answer){
            answerArr[idx++] = city;
        }
        
        return answerArr;
    }
    
    public boolean DFS(String ticket){
        String curr = ticket.split(" ")[1];
        answer.add(curr);
        visited.put(ticket, visited.get(ticket) + 1);
        
        if(answer.size() == N) return true;
        
        if(map.keySet().contains(curr)) {
            String newTicket;
            for(String next : map.get(curr)){
                newTicket = curr + " " + next;
                if(visited.get(newTicket) < many.get(newTicket)){
                    if(DFS(newTicket)) return true;
                }
            }
        }
        
        answer.remove(answer.size() - 1);
        visited.put(ticket, visited.get(ticket) - 1);
        return false;
    }
}