import java.util.*;
class Solution {
    static int answer;
    public int solution(int[] arrayA, int[] arrayB) {
        Arrays.sort(arrayA);
        Arrays.sort(arrayB);
        
        compareArr(arrayA, arrayB);
        compareArr(arrayB, arrayA);
        
        return answer;
    }
    
    public void compareArr(int[] arr1, int[] arr2){
        boolean[] visited = new boolean[arr1[0] + 1];
        
        nextI:
        for(int i = 2; i <= arr1[0] ; i++){
            if(visited[i]) continue;
            for(int num : arr1){
                if(num % i != 0){
                    int m = i, k = 1;
                    while(m * k <= arr1[0]) visited[m * k++] = true;
                    continue nextI;
                }
            }
            
            for(int num : arr2){
                if(num % i == 0) continue nextI;
            }
            
            answer = Math.max(answer, i);
        }
    }
}