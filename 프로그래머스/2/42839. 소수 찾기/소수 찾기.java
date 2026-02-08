import java.util.*;
class Solution {
    static String str;
    static boolean[] visited;
    static Set<Integer> nums;
    static int answer;
    public int solution(String numbers) {
        str = numbers;
        visited = new boolean[str.length()];
        nums = new HashSet();
        answer = 0;
        
        DFS(0);
        nums.remove(0);
        nums.remove(1);
        
        for(int num : nums){
            소수체크(num);
        }
        
        return answer;
    }
    
    public void DFS(int num){
        nums.add(num);
        for(int i = 0; i < str.length(); i++){
            if(!visited[i]){
                visited[i] = true;
                DFS(num * 10 + (str.charAt(i) - '0'));
                visited[i] = false;
            }
        }
    }
    
    public void 소수체크(int num){
        for(int i = 2; i <= Math.sqrt(num); i++){
            if(num % i == 0) return;
        }
        answer++;
    }
    
}