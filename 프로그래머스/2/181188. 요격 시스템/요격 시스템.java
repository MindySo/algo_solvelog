import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        Arrays.sort(targets, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[1] - b[1];
            }
        });
        
        int answer = 0;
        int lastShoot = -1;
        for(int[] target : targets){
            if(target[0] > lastShoot){
                lastShoot = target[1] - 1;
                answer++;
            }
        }
        
        return answer;
    }
}