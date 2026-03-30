import java.util.*;
class Solution {
    static int[][] map;
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        map = new int[51][51];
        int startR = 51;
        int startC = 0;
        
        for(int[] rec : rectangle){
            if(rec[1] < startR) {
                startC = rec[2];
                startR = rec[1];
            }else if(rec[1] == startR && rec[2] > startC) {
                startC = rec[2];
            }
            
            for(int i = rec[1]; i <= rec[3]; i++){
                map[i][rec[0]]++;
                map[i][rec[2]]++;
            }
            for(int i = rec[0]; i <= rec[2]; i++){
                map[rec[1]][i]++;
                map[rec[3]][i]++;
            }
            
            map[rec[1]][rec[0]]++;
            map[rec[1]][rec[2]]++;
            map[rec[3]][rec[0]]++;
            map[rec[3]][rec[2]]++;
        }
        
        int[] dr = new int[] {-1, 0, 1, 0};
        int[] dc = new int[] {0, 1, 0, -1};
        
        int point1 = characterY * 50 + characterX;
        int point2 = itemY * 50 + itemX;
        
        int r = startR, c = startC;
        int dir = 1, gotPoint = 0, cnt = 0, path = 0;
        
        while(true){
            int curr = r * 50 + c;
            if(curr == point1 || curr == point2) ++gotPoint;
            
            cnt++;
            if(gotPoint == 1) path++;
            
            dir = getDir(r, c, dir);
            r += dr[dir];
            c += dc[dir];
            
            if(r == startR && c == startC) break;
        }
        
        return Math.min(path, cnt - path);
    }
    
    public int getDir(int r, int c, int dir){
        if(map[r][c] == 3) return (dir + 1) % 4;
        else if(map[r][c] == 2) return (dir + 3) % 4;
        else return dir;
    }
}