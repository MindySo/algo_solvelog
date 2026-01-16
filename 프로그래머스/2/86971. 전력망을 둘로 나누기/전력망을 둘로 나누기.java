import java.util.*;

class Solution {
    static List<Integer>[] edges;
    static int[] sizes;
    static boolean[] visited;
    
    public int solution(int n, int[][] wires) {
        edges = new List[n + 1];
        sizes = new int[n + 1];
        visited = new boolean[n + 1];
        
        for(int i = 1; i <= n; i++){
            edges[i] = new ArrayList<Integer>();
        }
        
        for(int[] wire : wires){
            edges[wire[0]].add(wire[1]);
            edges[wire[1]].add(wire[0]);
        }
        
        DFS(1);
        
        int min = 1000;
        int gap;
        for(int i = 2; i <= n; i++){
            gap = Math.abs((sizes[1] - sizes[i]) - sizes[i]);
            min = Math.min(min, gap);
        }
        
        return min;
    }
    
    public int DFS(int node){
        visited[node] = true;
        sizes[node] = 1;
        for(int child : edges[node]){
            if(!visited[child]){
                sizes[node] += DFS(child);
            }
        }
        return sizes[node];
    }
}