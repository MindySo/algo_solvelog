import java.util.*;
class Solution {
    public int solution(int[] a) {
        Map<Integer, Integer> map = new HashMap();
        List<Integer> list = new ArrayList();
        for(int i : a) {
            if(!map.containsKey(i)) list.add(i);
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        Collections.sort(list, new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                return map.get(o2) - map.get(o1);
            }
        });
        
        int answer = 0;
        
        for(int i : list){
            if(map.get(i) < answer) break;
            
            int idx = 0;
            int cnt = 0;
            
            while(idx + 1 < a.length){
                if((a[idx] == i || a[idx + 1] == i) 
                  && !(a[idx] == i && a[idx + 1] == i)){
                    cnt++;
                    idx += 2;
                }else{
                    idx++;
                }
            }
            
            answer = Math.max(answer, cnt * 2);
        }

        return answer;
    }
}