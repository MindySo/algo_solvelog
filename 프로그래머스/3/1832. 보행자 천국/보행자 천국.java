import java.util.*;
class Solution {
    static int MOD = 20170805;
    public int solution(int m, int n, int[][] cityMap) {
        int[][] routes = new int[m][n];
        boolean[][] visited = new boolean[m][n];
        
        PriorityQueue<Integer> pq = new PriorityQueue(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                return (o2 / n + 1) * (o2 % n + 1) - (o1 / n + 1) * (o1 % n + 1);
            }
        });
        
        int[] dr = {0, -1};
        int[] dc = {-1, 0};
        
        routes[m - 1][n - 1] = 1;
        pq.add((m - 1) * n + (n - 1));
        
        while(!pq.isEmpty()){
            int curr = pq.poll();
            int r = curr / n;
            int c = curr % n;
            
            if(visited[r][c]) continue;
            visited[r][c] = true;
            
            int newR, newC;
            direction:
            for(int d = 0; d < 2; d++){
                newR = r;
                newC = c;
                while(true){
                    newR += dr[d];
                    newC += dc[d];
                    if(newR < 0 || newR >= m || newC < 0 || newC >= n) continue direction;
                    if(cityMap[newR][newC] == 0) break;
                    if(cityMap[newR][newC] == 1) continue direction;
                }
                routes[newR][newC] += routes[r][c];
                routes[newR][newC] %= MOD;
                pq.add(newR * n + newC);
            }
        }
        
        return routes[0][0];
    }
}