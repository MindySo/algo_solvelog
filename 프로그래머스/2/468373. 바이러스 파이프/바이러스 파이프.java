import java.util.*;
class Solution {
    static int K;
    static List<String> orders;
    public int solution(int n, int infection, int[][] edges, int k) {
        boolean[][] visited = new boolean[n + 1][4];
        
        List<Integer>[][] gates = new List[n + 1][4];
        for(int i = 1; i <= n; i++)
            for(int j = 1; j <= 3; j++) gates[i][j] = new ArrayList();
        for(int[] edge : edges){
            gates[edge[0]][edge[2]].add(edge[1]);
            gates[edge[1]][edge[2]].add(edge[0]);
        }

        
        K = k;
        orders = new ArrayList();
        DFS("");
        
        int answer = 0;
        
        Queue<int[]> que = new ArrayDeque();
        for(String order : orders){
            int cnt = 0;
            visited = new boolean[n + 1][4];
            que.clear();
            que.add(new int[] {infection, 0});
            
            while(!que.isEmpty()){
                int[] temp = que.poll();
                int curr = temp[0];
                int idx = temp[1];
                
                if(!visited[curr][0]){
                    visited[curr][0] = true;
                    cnt++;
                }
                
                if(idx > 0){
                    int prevGate = order.charAt(idx - 1) - '0';
                    if(!visited[curr][prevGate]){
                        visited[curr][prevGate] = true;
                        for(int next : gates[curr][prevGate]){
                            if(!visited[next][0]) que.add(new int[] {next, idx});
                        }
                    }
                }
                
                if(idx == k) continue;
                int gate = order.charAt(idx) - '0';
                if(!visited[curr][gate]){
                    visited[curr][gate] = true;
                    for(int next : gates[curr][gate]){
                        if(!visited[next][0]) que.add(new int[] {next, idx + 1});
                    }
                }
                
                if(visited[curr][1] && visited[curr][2] && visited[curr][3]) continue;
                que.add(new int[] {curr, idx + 1});
            }
            answer = Math.max(answer, cnt);
        }
        
        return answer;
    }
    
    public void DFS(String order){
        if(order.length() == K) {
            orders.add(order);
            return;
        }
        for(int i = 1; i <= 3; i++) DFS(order + i);
    }
}