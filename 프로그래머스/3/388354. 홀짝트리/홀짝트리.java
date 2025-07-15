import java.util.*;

class Solution {
    static int[] nodes;
    static int[][] edges;
    
    static int maxNode;
    static int[] union;
    static int[] edgeCnt;
    static int[][] nodeTypeCnt;
    
    
    public int[] solution(int[] nodes, int[][] edges) {
        this.nodes = nodes;
        this.edges = edges;
        
        init();
        
        for(int[] edge : edges){
            setUnion(edge[0], edge[1]);
            edgeCnt[edge[0]]++;
            edgeCnt[edge[1]]++;
        }
        
        for(int i = 1; i <= maxNode; i++){
            if(union[i] == 0){  // 유효하지 않은 노드번호
                continue;
            }
            
            int currUnion = findUnion(i);
            
            if(i % 2 == edgeCnt[i] % 2){
                nodeTypeCnt[currUnion][0]++;
            }else{
                nodeTypeCnt[currUnion][1]++;
            }
        }
        
        
        int[] answer = new int[2];
        for(int i = 1; i <= maxNode; i++){
            if(nodeTypeCnt[i][0] == 1){
               answer[0]++;
            }
            if(nodeTypeCnt[i][1] == 1){
               answer[1]++;
            }
        }
    
        return answer;
    }
    
    static void init(){
        maxNode = 0;
        for(int node : nodes){
            maxNode = Math.max(maxNode, node);
        }
        
        union = new int[maxNode + 1];
        edgeCnt = new int[maxNode + 1];
        nodeTypeCnt = new int[maxNode + 1][2];
        
        for(int node : nodes){
            union[node] = node;
        }
    }
    
    static void setUnion(int a, int b){
        int rootA = findUnion(a);
        int rootB = findUnion(b);
        if(rootA != rootB){
            union[rootA] = rootB;
        }
    }
    
    static int findUnion(int curr){
        if(curr == union[curr]){
            return union[curr];
        }
        return union[curr] = findUnion(union[curr]);
    }
}