import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        List<Integer>[] graph = new List[n + 1];
        for(int i = 1; i <= n; i++) graph[i] = new ArrayList();
        
        for(int[] e : edge){
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }
        
        boolean[] visited = new boolean[n + 1];
        int max = 1, maxCnt = 0;
        
        Queue<int[]> que = new ArrayDeque();
        que.add(new int[] {1, 0});
        
        while(!que.isEmpty()){
            int[] temp = que.poll();
            int node = temp[0];
            int dist = temp[1];
            
            if(visited[node]) continue;
            
            visited[node] = true;
            if(dist >= max){
                if(dist > max){
                    max = dist;
                    maxCnt = 0;
                }
                maxCnt++;
            }
            
            for(int next : graph[node]) {
                if(!visited[next]) que.add(new int[] {next, dist + 1});
            }
        }
        
        return maxCnt;
    }
}