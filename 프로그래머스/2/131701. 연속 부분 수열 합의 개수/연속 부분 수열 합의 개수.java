import java.util.*;
class Solution {
    public int solution(int[] elements) {
        Set<Integer> set = new HashSet();
        int sum;
        for(int s = 1; s <= elements.length; s++){
            for(int i = 0; i < elements.length; i++){
                sum = 0;
                for(int l = 1; l <= s; l++){
                    sum += elements[(i + l) % elements.length];
                }
                set.add(sum);
            }
        }
        return set.size();
    }
}