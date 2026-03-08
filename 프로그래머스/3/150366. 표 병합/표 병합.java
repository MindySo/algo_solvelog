import java.util.*;
class Solution {
    static String[][] data;
    static int[][] point;
    static Map<String, Set<Integer>> valueMap;
    static Map<Integer, Set<Integer>> mergeMap;
    
    public String[] solution(String[] commands) {
        data = new String[51][51];
        point = new int[51][51];
        valueMap = new HashMap();
        mergeMap = new HashMap();
        StringBuilder sb = new StringBuilder();
        
        valueMap.put("", new HashSet());
        for(int i = 0; i < 51; i++){
            for(int j = 0; j < 51; j++){
                int adr = i * 51 + j;
                data[i][j] = "";
                point[i][j] = adr;
                mergeMap.put(adr, new HashSet());
                mergeMap.get(adr).add(adr);
                valueMap.get("").add(adr);
            }
        }
        
        for(String command : commands){
            String[] words = command.split(" ");
            if(command.charAt(0) == 'U' && command.charAt(1) == 'P'){
                if(words.length == 3){
                    if(words[1].equals(words[2])) continue;
                    updateVal(words[1], words[2]);
                }else{
                    int adr = point[Integer.parseInt(words[1])][Integer.parseInt(words[2])];
                    update(adr, words[3]);
                }
            }else if(command.charAt(0) == 'M'){
                int adr1 = point[Integer.parseInt(words[1])][Integer.parseInt(words[2])];
                int adr2 = point[Integer.parseInt(words[3])][Integer.parseInt(words[4])];
                if(adr1 == adr2) continue;
                merge(adr1, adr2);
            }else if(command.charAt(0) == 'U'){
                unMerge(Integer.parseInt(words[1]), Integer.parseInt(words[2]));
            }else{
                int adr = point[Integer.parseInt(words[1])][Integer.parseInt(words[2])];
                String value = data[adr / 51][adr % 51];
                sb.append(value.equals("") ? "EMPTY" : value);
                sb.append(" ");
            }
        }
        
        return sb.toString().split(" ");
    }
    
    public void update(int adr, String value){
        int r = adr / 51;
        int c = adr % 51;
        valueMap.get(data[r][c]).remove(adr);
        data[r][c] = value;
        if(!valueMap.containsKey(value)) valueMap.put(value, new HashSet());
        valueMap.get(value).add(adr);
    }
    
    public void updateVal(String value1, String value2){
        if(!valueMap.containsKey(value1)) return;
        if(!valueMap.containsKey(value2)) valueMap.put(value2, new HashSet());
        for(int adr : valueMap.get(value1)){
            int r = adr / 51;
            int c = adr % 51;
            data[r][c] = value2;
            valueMap.get(value2).add(adr);
        }
        valueMap.get(value1).clear();
    }
    
    public void merge(int adr1, int adr2){
        int r1 = adr1 / 51;
        int c1 = adr1 % 51;
        String value = data[r1][c1].equals("") ? data[adr2 / 51][adr2 % 51] : data[r1][c1];
        
        if(adr2 < adr1){
            int temp = adr1;
            adr1 = adr2;
            adr2 = temp;
        }
        
        for(int merged : mergeMap.get(adr2)){
            mergeMap.get(adr1).add(merged);
            point[merged / 51][merged % 51] = adr1;
        }
        update(adr1, value);
        point[adr2 / 51][adr2 % 51] = adr1;
        mergeMap.get(adr2).clear();
    }
    
    public void unMerge(int r, int c){
        int adr = point[r][c];
        String value = data[adr / 51][adr % 51];
        
        for(int merged : mergeMap.get(adr)){
            point[merged / 51][merged % 51] = merged;
            mergeMap.get(merged).add(merged);
            update(merged, "");
        }
        
        mergeMap.get(adr).clear();
        mergeMap.get(adr).add(adr);
        
        update(r * 51 + c, value);
    }
}