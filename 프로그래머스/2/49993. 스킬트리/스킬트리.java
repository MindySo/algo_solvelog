import java.util.*;
class Solution {
    public int solution(String skill, String[] skill_trees) {
        int answer = 0;
        int[] order = new int[26];
        Arrays.fill(order, -1);
        boolean[] visited = new boolean[26];
        
        int curr, prev = skill.charAt(0) - 'A';
        for(int i = 1; i < skill.length(); i++){
            curr = skill.charAt(i) - 'A';
            order[curr] = prev;
            prev = curr;
        }
        
        nextTree:
        for(String str : skill_trees){
            Arrays.fill(visited, false);
            for(char c : str.toCharArray()){
                curr = c - 'A';
                if(order[curr] != -1 && !visited[order[curr]]) continue nextTree;
                visited[curr] = true;
            }
            answer++;
        }
        
        return answer;
    }
}