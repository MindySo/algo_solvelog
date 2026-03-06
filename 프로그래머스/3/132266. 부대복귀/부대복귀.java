import java.util.*;
class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        List<Integer>[] graph = new List[n + 1];
        for(int i = 0; i < n + 1; i++) graph[i] = new ArrayList();
        
        for(int[] road : roads){
            graph[road[0]].add(road[1]);
            graph[road[1]].add(road[0]);
        }
        
        int[] dest = new int[n + 1];
        Arrays.fill(dest, Integer.MAX_VALUE);
        Queue<int[]> que = new ArrayDeque();
        
        que.add(new int[] {destination, 0});
        while(!que.isEmpty()){
            int[] temp = que.poll();
            int from = temp[0];
            int far = temp[1];
            if(dest[from] > far){
                dest[from] = far;
                for(int to : graph[from]) que.add(new int[] {to, far + 1});
            }
        }
        
        int[] answer = new int[sources.length];
        for(int i = 0; i < sources.length; i++){
            answer[i] = dest[sources[i]] == Integer.MAX_VALUE ? -1 : dest[sources[i]];
        }
        
        return answer;
    }
}