import java.util.*;
class Solution {
    public int solution(int[][] scores) {
        Map<Integer, Integer> maxs = new TreeMap(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                return o2 - o1;
            }    
        });
        
        for(int[] score : scores){
            maxs.put(score[0], Math.max(maxs.getOrDefault(score[0], 0), score[1]));
        }
        
        int max = 0;
        for(int s1 : maxs.keySet()){
            int curr = maxs.get(s1);
            maxs.put(s1, max);
            max = Math.max(max, curr);
        }
        
        if(scores[0][1] < maxs.get(scores[0][0])) return -1;
        
        int target = scores[0][0] + scores[0][1];
        int answer = 0;
        for(int[] score : scores){
            if(score[0] + score[1] > target && score[1] >= maxs.get(score[0])) answer++;
        }
        
        return answer + 1;
    }
}