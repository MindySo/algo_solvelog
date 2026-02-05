import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int[] visited = new int[10];
        Queue<Integer> que = new ArrayDeque();
        
        for(int i = 0; i < priorities.length; i++){
            que.add(i);
            visited[priorities[i]]++;
        }
        
        int p = 9, order = 0, curr, queSize;
        
        process:
        while(!que.isEmpty()){
            while(visited[p] == 0) p--;
            queSize = que.size();
            
            for(int i = 0; i < queSize; i++){
                curr = que.poll();
                
                if(priorities[curr] == p){
                    order++;
                    if(curr == location) break process;
                    
                    visited[p]--;
                    if(visited[p] == 0) break;
                }else{
                    que.add(curr);
                }
            }
        }
        
        return order;
    }
}