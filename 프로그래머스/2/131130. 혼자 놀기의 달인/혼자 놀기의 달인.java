import java.util.*;

class Solution {
    static int[] cardArray;
    static boolean[] visited;
    
    public int solution(int[] cards) {
        cardArray = cards;
        visited = new boolean[cards.length];
        int max = 0;
        
        int first, second;
        for(int i = 0; i < cards.length; i++){
            Arrays.fill(visited, false);
            first = open(i, 0);
            
            for(int j = 0; j < cards.length; j++){
                second = open(j, 0);
                max = Math.max(max, first * second);
            }
        }
        
        return max;
    }
    
    public int open(int idx, int score){
        if(visited[idx]){
            return score;
        }else{
            visited[idx] = true;
            return open(cardArray[idx] - 1, score + 1);
        }
    }
}