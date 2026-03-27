import java.util.*;
class Solution {
    public int solution(int[] a) {
        Map<Integer, List<Integer>> map = new HashMap();
        List<Integer> list = new ArrayList();
        for(int i = 0; i < a.length; i++) {
            int num = a[i];
            if(!map.containsKey(num)) {
                list.add(num);
                map.put(num, new ArrayList());
            }
            map.get(num).add(i);
        }
        
        Collections.sort(list, new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                return map.get(o2).size() - map.get(o1).size();
            }
        });
        
        
        boolean[] visited = new boolean[a.length];
        int answer = 0;
        for(int num : list){
            Arrays.fill(visited, false);
            if(map.get(num).size() <= answer) break;
            
            List<Integer> idxs = map.get(num);
            int i = 0;
            int cnt = 0;
            
            while(i < idxs.size()){
                int idx = idxs.get(i);
                
                if(idx > 0 && a[idx - 1] != num && !visited[idx - 1]) {
                    visited[idx - 1] = true;
                    cnt++;
                }else if(idx + 1 < a.length && a[idx + 1] != num && !visited[idx + 1]){
                    visited[idx + 1] = true;
                    cnt++;
                }
                
                i++;
            }
            
            answer = Math.max(answer, cnt);
        }

        return answer * 2;
    }
}