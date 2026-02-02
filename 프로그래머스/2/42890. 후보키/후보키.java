import java.util.*;

class Solution {
    static String[][] data;
    static Set<List<Integer>> cKeys;
    static int columnSize;
    public int solution(String[][] relation) {
        data = relation;
        cKeys = new HashSet();
        columnSize = relation[0].length;
        
        for(int i = 0; i < columnSize; i++){
            DFS(i, new ArrayList());
        }
        
        return cKeys.size();
    }
    
    public void DFS(int idx, List<Integer> columns){
        if(idx == columnSize) return;
        
        columns.add(idx);
        Set<String> tuples = new HashSet();
        
        boolean isCKey = true;
        for(String[] tuple : data){
            String str = "";
            for(int c : columns){
                str += tuple[c];
            }
            if(tuples.contains(str)){
                isCKey = false;
                break;
            }
            tuples.add(str);
        }
        
        if(isCKey){
            Set<List<Integer>> removeSet = checkMinimal(columns);
            for(List<Integer> notCKey : removeSet){
                cKeys.remove(notCKey);
            }
            
            cKeys.add(columns);
            return;
        }
        
        
        for(int i = idx + 1; i < columnSize; i++){
            List<Integer> newList = new ArrayList();
            newList.addAll(columns);
            DFS(i, newList);
        }
    }
    
    public Set<List<Integer>> checkMinimal(List<Integer> columns){
        Set<List<Integer>> removeSet = new HashSet();
        for(List<Integer> cKey : cKeys){
            if(cKey.containsAll(columns)) removeSet.add(cKey);
        }
        return removeSet;
    }
}