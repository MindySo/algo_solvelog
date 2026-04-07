import java.util.*;
class Solution {
    public long solution(int[] a, int[][] edges) {
        long sum = 0;
        long[] val = new long[a.length];
        for(int i = 0; i < a.length; i++) {
            sum += a[i];
            val[i] = a[i];
        }
        if(sum != 0) return -1;
        
        Set<Integer>[] graph = new Set[a.length];
        for(int i = 0; i < a.length; i++) graph[i] = new HashSet();
        for(int[] edge : edges){
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        Queue<Integer> que = new ArrayDeque();
        for(int i = 0; i < a.length; i++) if(graph[i].size() == 1) que.add(i);
        
        long answer = 0;
        while(!que.isEmpty()){
            int curr = que.poll();
            for(int parent : graph[curr]) {
                val[parent] += val[curr];
                answer += Math.abs(val[curr]);
                graph[parent].remove(curr);
                if(graph[parent].size() == 1) que.add(parent);
            }
        }
        
        return answer;
    }
}