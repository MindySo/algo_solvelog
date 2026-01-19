import java.util.*;

class Solution {
    static 
    
    public int solution(int[][] points, int[][] routes) {
        Map<String, Integer> map = new HashMap<>();
        
        Set<String>[] setArr = new HashSet[routes.length + 1];
        for(int i = 1; i < routes.length + 1; i++){
            setArr[i] = new HashSet<>();
        }
        
        for(int i = 0; i < routes.length; i++){
            int count = 1;
            for(int j = 0; j < routes[i].length - 1; j++){
                int startPoint = routes[i][j]; //2
                int endPoint = routes[i][j + 1]; //3
                
                int startR = points[startPoint - 1][0];
                int startC = points[startPoint - 1][1];
                int endR = points[endPoint - 1][0];
                int endC = points[endPoint - 1][1];
                int no = i + 1;
                count--;
                
                if(startR <= endR){
                    for(int r = startR; r <= endR; r++){
                        setArr[no].add(r + " " + startC + " " + count++);
                    }
                }else{
                    for(int r = startR; r >= endR; r--){
                        setArr[no].add(r + " " + startC + " " + count++);
                    }
                }
                    
                if(startC < endC){
                    for(int c = startC + 1; c <= endC; c++){
                        setArr[no].add(endR + " " + c + " " + count++);
                    }
                }else if(startC > endC){
                    for(int c = startC - 1; c >= endC; c--){
                        setArr[no].add(endR + " " + c + " " + count++);
                    }
                }
            }
        }
        
        for(int i = 1; i < routes.length + 1; i++){
            for(String s : setArr[i]){
                if(map.containsKey(s)){
                    map.put(s, map.get(s) + 1);
                }
                else{
                    map.put(s, 0);
                }
            }
        }
        
        int answer = 0;
        for(String key : map.keySet()){
            if(map.get(key) > 0){
                answer++;
            }
        }
        return answer;
    }
}