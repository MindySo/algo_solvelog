import java.util.*;
class Solution {
    public int[] solution(int n, long k) {
        long module = 1;
        List<Integer> list = new ArrayList();
        for(int i = 1; i <= n; i++) {
            module *= i;
            list.add(i);
        }
        
        int[] answer = new int[n];
        
        for(int i = n; i >= 1; i--){
            module /= i;
            int prev = (int)(k / module);
            if(k % module == 0) prev--;
            
            answer[n - i] = list.get(prev);
            list.remove(prev);
            k -= module * prev;
        }
        
        return answer;
    }
}