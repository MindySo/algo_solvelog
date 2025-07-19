import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        final int INF = 1_000_000;
        int[] in = new int[INF + 1];
        Arrays.fill(in, -1);
        
        List<Integer>[] nodes = new List[INF + 1];
        for(int i = 0 ; i < INF + 1 ; i++){
            nodes[i] = new ArrayList();
        }
        
        int from, to;
        for(int[] edge : edges){
            from = edge[0];
            to = edge[1];
            nodes[from].add(to);
            
            if(in[from] == -1){
                in[from] = 0;
            }
            if(in[to] == -1){
                in[to] = 0;
            }
            in[to]++;
        }
        
        int root = -1;
        for(int i = 1 ; i < INF + 1 ; i++){
            if(in[i] == 0 && nodes[i].size() >= 2){
                root = i;
                break;
            }
        }
        
        Arrays.fill(in, 0);
        
        int doughnut = 0, bar = 0, eight = 0;
        for(int firstNode : nodes[root]){
            int curr = firstNode;
            
            while(in[curr] != 1){
                in[curr] = 1;
                if(nodes[curr].isEmpty() || nodes[curr].size() >= 2){
                    break;
                }else{
                    curr = nodes[curr].get(0);
                }
            }
            
            if(nodes[curr].isEmpty()){
                bar++;
            }else if(nodes[curr].size() == 1){
                doughnut++;
            }else{
                eight++;
            }
        }
        
        int[] answer = {root, doughnut, bar, eight};
        return answer;
    }
}