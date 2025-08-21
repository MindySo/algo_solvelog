import java.util.*;

class Solution {
    static int N;
    static int INF = Integer.MAX_VALUE;
    static int[][] map;
    static int[][] dist;
    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};
    public int solution(int[][] board) {
        init(board);
        dijkstra();
        
        int answer = dist[N - 1][N - 1];
        return answer;
    }
    
    static void init(int[][] board){
        map = board;
        N = map.length;
        dist = new int[N][N];
        for(int i = 0 ; i < N ; i++){
            Arrays.fill(dist[i], INF);
        }
    }
    
    static void dijkstra(){
        // int[] : r, c, cost, dir
        // dir : 0(상), 1(우), 2(하), 3(좌)
        PriorityQueue<int[]> pq = new PriorityQueue(new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[3] - o2[3];
            }
        });
        
        pq.add(new int[] {0, 0, 0, -1});
        
        int[] temp;
        int r, c, cost, dir, newR, newC, newCost;
        while(!pq.isEmpty()){
            temp = pq.poll();
            r = temp[0];
            c = temp[1];
            cost = temp[2];
            dir = temp[3];
            
            if(cost > dist[r][c]){
                continue;
            }
            
            dist[r][c] = cost;
            // System.out.println(r + ", " + c + ", " + dir + " : " + cost);

            for(int d = 0 ; d < 4 ; d++){
                newR = r + dr[d];
                newC = c + dc[d];
                if(valid(newR, newC)){
                    if(dir == d || dir == -1){
                        newCost = cost + 100;
                    }else{
                        newCost = cost + 600;
                    }
                    pq.add(new int[] {newR, newC, newCost, d});
                }
            }
        }
    }
    
    static boolean valid(int r, int c){
        if(r >= 0 && r < N && c >= 0 && c < N && map[r][c] == 0){
            return true;
        }
        return false;
    }
}