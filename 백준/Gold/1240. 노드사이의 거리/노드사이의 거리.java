import java.io.*;
import java.util.*;

public class Main {
    static ArrayList<ArrayList<Node>> graph;
    static boolean[] visited;
    
    static class Node {
        int vertex;
        int weight;
        
        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }
        
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w));
        }
        
        StringBuilder sb = new StringBuilder();
        
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            
            visited = new boolean[N + 1];
            int distance = dfs(start, end, 0);
            sb.append(distance).append("\n");
        }
        
        System.out.print(sb);
        br.close();
    }
    
    static int dfs(int current, int target, int distance) {
        if (current == target) {
            return distance;
        }
        
        visited[current] = true;
        
        for (Node next : graph.get(current)) {
            if (!visited[next.vertex]) {
                int result = dfs(next.vertex, target, distance + next.weight);
                if (result != -1) {
                    return result;
                }
            }
        }
        
        return -1;
    }
}