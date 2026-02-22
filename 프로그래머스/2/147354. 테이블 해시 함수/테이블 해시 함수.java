import java.util.*;
class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        List<int[]> list = new ArrayList();
        for(int i = 0; i < data.length; i++){
            list.add(data[i]);
        }
        
        Collections.sort(list, new Comparator<int[]>(){
           public int compare(int[] o1, int[] o2){
               if(o1[col - 1] != o2[col - 1]) return o1[col - 1] - o2[col - 1];
               return o2[0] - o1[0];
           } 
        });
        
        int answer = 0;
        for(int i = 0; i < data[0].length; i++){
            answer += list.get(row_begin - 1)[i] % row_begin;
        }
        
        for(int i = row_begin + 1; i <= row_end; i++){
            int k = 0;
            for(int j = 0; j < data[0].length; j++){
                k += list.get(i - 1)[j] % i;
            }
            answer ^= k;
        }
        
        return answer;
    }
}