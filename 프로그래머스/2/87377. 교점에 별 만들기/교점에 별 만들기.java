import java.util.*;
class Solution {
    public String[] solution(int[][] line) {
        Set<long[]> set = new HashSet();
        long minX = Long.MAX_VALUE;
        long minY = Long.MAX_VALUE;
        long maxX = Long.MIN_VALUE;
        long maxY = Long.MIN_VALUE;
        
        long a, b, c, d, e, f;
        long 분자1, 분자2, 분모;
        double x, y;
        long longX, longY;
        for(int i = 0; i < line.length; i++){
            a = line[i][0];
            b = line[i][1];
            e = line[i][2];
            for(int j = i + 1; j < line.length; j++){
                c = line[j][0];
                d = line[j][1];
                f = line[j][2];
                
                분모 = a * d - b * c;
                분자1 = b * f - e * d;
                분자2 = e * c - a * f;
                    
                if(분모 == 0) continue;
                x = (double)분자1 / (double)분모;
                y = (double)분자2 / (double)분모;
                
                if(x % 1 == 0 && y % 1 == 0){
                    longX = (long)x;
                    longY = (long)y * -1;
                    set.add(new long[] {longY, longX});
                    minX = Math.min(minX, longX);
                    minY = Math.min(minY, longY);
                    maxX = Math.max(maxX, longX);
                    maxY = Math.max(maxY, longY);
                }
            }
        }
        
        boolean[][] arr = new boolean[(int)(maxY - minY + 1)][(int)(maxX - minX + 1)];
        for(long[] dot : set){
            arr[(int)(dot[0] - minY)][(int)(dot[1] - minX)] = true;
        }
        
        StringBuilder sb;
        String[] answer = new String[arr.length];
        int idx = 0;
        for(boolean[] r : arr){
            sb = new StringBuilder();
            for(boolean bool : r){
                if(bool){
                    sb.append("*");
                }else{
                    sb.append(".");
                }
            }
            answer[idx++] = sb.toString();
        }
        
        return answer;
    }
}