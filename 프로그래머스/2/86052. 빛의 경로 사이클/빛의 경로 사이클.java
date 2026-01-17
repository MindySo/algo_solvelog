import java.util.*;

class Solution {
    public int[] solution(String[] grid) {
        int N = grid.length;
        int M = grid[0].length();
        char[][] map = new char[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j] = grid[i].charAt(j);
            }
        }
        
        boolean[][][] visited = new boolean[N][M][4];
        List<Integer> answer = new ArrayList();
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        Queue<int[]> que = new ArrayDeque();
        // {r, c, d, l}
        
        int[] curr;
        int r, c, d, l;
        for(int i = 0; i < N ; i++){
            for(int j = 0; j < M; j++){
                for(int dir = 0; dir < 4; dir++){
                    if(!visited[i][j][dir]){
                        que.add(new int[] {i, j, dir, 1});
                    }
                    while(!que.isEmpty()){
                        curr = que.poll();
                        r = curr[0];
                        c = curr[1];
                        d = curr[2];
                        l = curr[3];
                        
                        visited[r][c][d] = true;
                        
                        r = (r + dr[d] + N) % N;
                        c = (c + dc[d] + M) % M;
                        
                        if(map[r][c] == 'L'){
                            d = (d + 3) % 4;
                        }else if(map[r][c] == 'R'){
                            d = (d + 1) % 4;
                        }
                        
                        if(visited[r][c][d]){
                            answer.add(l);
                        }else{
                            que.add(new int[] {r, c, d, l + 1});
                        }
                    }
                }
            }
        }
        
        Collections.sort(answer);
        return answer.stream().mapToInt(i -> i).toArray();
    }
}