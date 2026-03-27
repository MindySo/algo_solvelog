import java.util.*;
class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        
        Set<Integer> summitSet = new HashSet();
        for(int s : summits) summitSet.add(s);

        Set<Integer> gateSet = new HashSet();
        for(int g : gates) gateSet.add(g);
        
        List<int[]>[] graph = new List[n + 1];
        for(int i = 0; i <= n; i++) graph[i] = new ArrayList();
        
        for(int[] p : paths){
            graph[p[0]].add(new int[] {p[1], p[2]});
            graph[p[1]].add(new int[] {p[0], p[2]});
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                if(dist[o1] != dist[o2]) return dist[o1] - dist[o2];
                return o1 - o2;
            }
        });
        
        int totalMin = Integer.MAX_VALUE;
        int summitNum = Integer.MAX_VALUE;
        Arrays.fill(dist, Integer.MAX_VALUE);

        for(int gate : gates){
            dist[gate] = 0;
            pq.add(gate);
        }
            
        while(!pq.isEmpty()){
            int curr = pq.poll();

            if(summitSet.contains(curr)){
                if(dist[curr] < totalMin){
                    totalMin = dist[curr];
                    summitNum = curr;
                }else if(dist[curr] == totalMin && curr < summitNum){
                    summitNum = curr;
                }
                continue;
            }
            
            if(visited[curr]) continue;
            visited[curr] = true;

            for(int[] node : graph[curr]){
                int next = node[0];
                int t = node[1];
                int newDist = Math.max(dist[curr], t);

                if(newDist < dist[next]) {
                    dist[next] = newDist;
                    if(!visited[next]) pq.add(next);
                }
            }
        }
        
        return new int[] {summitNum, totalMin};
    }
}
