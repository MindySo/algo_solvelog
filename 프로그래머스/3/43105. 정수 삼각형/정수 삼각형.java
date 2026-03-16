import java.util.*;
class Solution {
    public int solution(int[][] triangle) {
        for(int i = 1; i < triangle.length; i++){
            triangle[i][0] += triangle[i - 1][0];
            for(int j = 1; j < i; j++){
                triangle[i][j] += Math.max(triangle[i - 1][j - 1], triangle[i - 1][j]);
            }
            triangle[i][i] += triangle[i - 1][i - 1];
        }
        
        int answer = 0;
        for(int sum : triangle[triangle.length - 1]) answer = Math.max(answer, sum);
        return answer;
    }
}