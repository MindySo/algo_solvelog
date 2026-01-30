import java.util.*;
class Solution {
    public int[] solution(String s) {
        List<List<Integer>> tuple = new ArrayList();
        int max = 0;
        int idx = 0;
        
        char curr;
        int number = 0;
        List<Integer> list = new ArrayList();
        for(int i = 1; i < s.length() - 1; i++){
            curr = s.charAt(i);
            if(curr == '{'){
                list = new ArrayList();
            }else if(curr == ','){
                if(s.charAt(i - 1) != '}'){
                    list.add(number);
                    number = 0;
                }else continue;
            }else if(curr == '}'){
                list.add(number);
                number = 0;
                tuple.add(list);
            }else {
                number = number * 10 + (curr - '0');
            }
        }
        
        Collections.sort(tuple, new Comparator<List>(){
            public int compare(List o1, List o2){
                return o1.size() - o2.size();
            }
        });
        
        int[] answer = new int[tuple.get(tuple.size() - 1).size()];
        boolean[] visited = new boolean[100_001];
        
        nextList:
        for(List<Integer> t : tuple){
            for(int num : t){
                if(!visited[num]){
                   answer[t.size() - 1] = num;
                    visited[num] = true;
                    continue nextList;
                }
            }
        }
        
        return answer;
    }
}