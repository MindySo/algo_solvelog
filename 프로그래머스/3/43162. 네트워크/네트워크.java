import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        List<Integer>[] graph = new List[n];
        for(int i = 0; i < n; i++) graph[i] = new ArrayList();
        
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) 
                if(computers[i][j] == 1) graph[i].add(j); 
        
        
        Queue<Integer> que = new ArrayDeque();
        boolean[] visited = new boolean[n];
        int answer = 0;
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;
            
            answer++;
            que.add(i);
            
            while(!que.isEmpty()){
                int curr = que.poll();
                if(visited[curr]) continue;
                visited[curr] = true;
                for(int next : graph[curr]) if(!visited[next]) que.add(next);
            }
        }

        return answer;
    }
}