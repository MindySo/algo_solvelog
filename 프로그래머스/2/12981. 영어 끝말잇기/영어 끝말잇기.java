import java.util.*;
class Solution {
    public int[] solution(int n, String[] words) {
        int turn = 1, idx = 0;
        char last = words[0].charAt(0), start;
        Set<String> set = new HashSet();
        
        while(idx < words.length){
            start = words[idx].charAt(0);
            if(start != last || set.contains(words[idx])) return new int[] {turn, idx / n + 1};
            
            set.add(words[idx]);
            last = words[idx].charAt(words[idx++].length() - 1);
            if(++turn > n) turn = 1;
        }

        return new int[] {0, 0};
    }
}