import java.util.*;
class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        Deque<String> deque = new ArrayDeque();
        Set<String> set = new HashSet();
        
        for(int i = 0; i < cities.length; i++){
            String city = "";
            for(char c : cities[i].toCharArray()){
                if(c >= 'a') c -= 32;
                city += c;
            }
            
            if(set.contains(city)){
                answer += 1;
                
                deque.remove(city);
                deque.addLast(city);
            }else{
                answer += 5;

                if(cacheSize == 0) continue;
                
                if(set.size() >= cacheSize) set.remove(deque.removeFirst());
                deque.addLast(city);
                set.add(city);
            }
        }
        
        return answer;
    }
}