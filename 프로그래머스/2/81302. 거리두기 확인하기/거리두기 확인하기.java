import java.util.*;
class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[] {1, 1, 1, 1, 1};
        char[][][] map = new char[5][5][5];
        for(int r = 0; r < 5; r++){
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    map[r][i][j] = places[r][i].charAt(j);
                }
            }
        }
        
        int[] dr = new int[] {-1, 0, 0, 1};
        int[] dc = new int[] {0, -1, 1, 0};
        Queue<int[]> que = new ArrayDeque();
        int[] curr;
        int r, c, dist, newR, newC;
        
        roomCheck:
        for(int room = 0; room < 5; room++){
            que.clear();
            for(int i = 0; i < 5; i++){
                for(int j = 0; j < 5; j++){
                    if(map[room][i][j] == 'P'){
                        que.add(new int[] {i, j, 0});
                        map[room][i][j] = 'V';
                    }
                    while(!que.isEmpty()){
                        curr = que.poll();
                        r = curr[0];
                        c = curr[1];
                        dist = curr[2];

                        if(map[room][r][c] == 'P'){
                            answer[room] = 0;
                            continue roomCheck;
                        }
                        if(dist >= 2) continue;

                        for(int d = 0; d < 4; d++){
                            newR = r + dr[d];
                            newC = c + dc[d];
                            if(newR >= 0 && newR < 5 && newC >= 0 && newC < 5 
                               && map[room][newR][newC] != 'X'){
                                que.add(new int[] {newR, newC, dist + 1});
                            }
                        }
                    } // while
                } // j
            } // i
        } // room
        
        return answer;
    }
}