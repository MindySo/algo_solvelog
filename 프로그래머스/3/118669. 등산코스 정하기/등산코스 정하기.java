import java.util.*;
class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] dist = new int[n + 1];
        Map<String, Integer> time = new HashMap();
        boolean[] visited = new boolean[n + 1];
        
        Set<Integer> summitSet = new HashSet();
        for(int s : summits) summitSet.add(s);

        Set<Integer> gateSet = new HashSet();
        for(int g : gates) gateSet.add(g);
        
        List<Integer>[] graph = new List[n + 1];
        for(int i = 0; i <= n; i++) graph[i] = new ArrayList();
        
        for(int[] p : paths){
            graph[p[0]].add(p[1]);
            graph[p[1]].add(p[0]);
            time.put(p[0] + "/" + p[1], p[2]);
        }
        
        PriorityQueue<Integer> pq = new PriorityQueue(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                if(dist[o1] != dist[o2]) return dist[o1] - dist[o2];
                return o1 - o2;
            }
        });
        
        int totalMin = Integer.MAX_VALUE;
        int summitNum = Integer.MAX_VALUE;
        
        for(int gate : gates){
            pq.clear();
            Arrays.fill(dist, Integer.MAX_VALUE);
            Arrays.fill(visited, false);
            
            dist[gate] = 0;
            pq.add(gate);
            while(!pq.isEmpty()){
                int curr = pq.poll();
                
                if(summitSet.contains(curr)){
                    if(dist[curr] < totalMin){
                        totalMin = dist[curr];
                        summitNum = curr;
                    }else if(dist[curr] == totalMin && curr < summitNum){
                        summitNum = curr;
                    }
                    break;
                }
                
                if(visited[curr]) continue;
                visited[curr] = true;
                
                for(int next : graph[curr]){
                    int t = time.get(Math.min(curr, next) + "/" + Math.max(curr, next));
                    int newDist = Math.max(dist[curr], t);
                    
                    if(newDist < dist[next] && !visited[next]) {
                        dist[next] = newDist;
                        if(!gateSet.contains(next)) pq.add(next);
                    }
                }
            }
        }
        
        return new int[] {summitNum, totalMin};
    }
}