import java.util.*;
class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        double x = startX, y = startY;
        int[] answer = new int[balls.length];
        int idx = 0;
        
        for(int[] ball : balls){
            double min = Double.MAX_VALUE;
            double a = ball[0], b = ball[1];
            double abs = 0, k = 0;
            
            if(y == b){
                if(x < a){
                    min = Math.min(min, Math.pow(a + x, 2));
                }else{
                    min = Math.min(min, Math.pow(2 * m - a - x, 2));
                }
            }else{
                abs = Math.abs(y - b);
                k = (abs * a) / (x + a);
                min = Math.min(min, getLength(a, k, x, abs - k));
                    
                abs = Math.abs(y - b);
                k = (abs * (m - a)) / (2 * m - x- a);
                min = Math.min(min, getLength(m - a, k, m - x, abs - k));
            }
            
            if(x == a){
                if(y < b){
                    min = Math.min(min, Math.pow(b + y, 2));
                }else{
                    min = Math.min(min, Math.pow(2 * n - b - y, 2));
                }
            }else{
                abs = Math.abs(x - a);
                k = (abs * (n - b)) / (2 * n - y - b);
                min = Math.min(min, getLength(n - b, k, n - y, abs - k));
                
                abs = Math.abs(x - a);
                k = (abs * b) / (y + b);
                min = Math.min(min, getLength(b, k, y, abs - k));
            }
            
            answer[idx++] = (int)min;
        }

        return answer;
    }
    
    public double getLength(double a, double b, double c, double d){
        return Math.round(Math.pow(
                    Math.sqrt(Math.pow(a, 2) + Math.pow(b, 2)) + 
                    Math.sqrt(Math.pow(c, 2) + Math.pow(d, 2))
                    , 2));
    }
}