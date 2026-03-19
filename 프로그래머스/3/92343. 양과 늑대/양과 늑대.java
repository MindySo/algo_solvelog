import java.util.*;
class Solution {
    static List<Integer>[] graph;
    static int[] who;
    static boolean[] visited;
    static int answer;
    
    public int solution(int[] info, int[][] edges) {
        graph = new List[info.length];
        who = info;
        visited = new boolean[info.length];
        for(int i = 0; i < info.length; i++) graph[i] = new ArrayList();
        for(int[] edge : edges) graph[edge[0]].add(edge[1]);
        
        answer = 0;
        DFS(0, 0, 0);
        
        return answer;
    }
    
    public void DFS(int curr, int sheep, int wolf){
        visited[curr] = true;
        
        if(who[curr] == 0) {
            answer = Math.max(answer, ++sheep);
        } else {
            if(++wolf == sheep) {
                visited[curr] = false;
                return;
            }
        }
            
        for(int i = 0; i < visited.length; i++){
            if(visited[i]){
                for(int next : graph[i]) if(!visited[next]) DFS(next, sheep, wolf);
            }
        }
        
        visited[curr] = false;
    }
    
}