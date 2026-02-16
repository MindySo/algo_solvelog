import java.util.*;
class Solution {
    public int solution(String str1, String str2) {
        Map<String, Integer> check1 = new HashMap();
        Map<String, Integer> check2 = new HashMap();
        double max = 0;
        double min = 0;
        
        for(int i = 0; i < str1.length() - 1; i++){
            if(!isEng(str1.charAt(i)) || !isEng(str1.charAt(i + 1))) continue;
            String s = "" + convert(str1.charAt(i)) + convert(str1.charAt(i + 1));
            check1.put(s, check1.getOrDefault(s, 0) + 1);
        }
        
        for(int i = 0; i < str2.length() - 1; i++){
            if(!isEng(str2.charAt(i)) || !isEng(str2.charAt(i + 1))) continue;
            String s = "" + convert(str2.charAt(i)) + convert(str2.charAt(i + 1));
            check2.put(s, check2.getOrDefault(s, 0) + 1);
        }
        
        for(String str : check1.keySet()){
            min += Math.min(check1.get(str), check2.getOrDefault(str, 0));
        }
        
        for(String str : check1.keySet()){
            if(check2.keySet().contains(str)){
                max += Math.max(check1.get(str), check2.getOrDefault(str, 0));
                check2.remove(str);
            }else{
                max += check1.get(str);
            }
        }
        
        for(String str : check2.keySet()){
            max += check2.get(str);
        }
        
        
        
        return max == 0 && min == 0 ? 65536 : (int)(min / max * 65536);
    }
    
    public boolean isEng(char c){
        if((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) return true;
        return false;
    }
    
    public char convert(char c){
        if(c >= 'a' && c <= 'z') c -= 32;
        return c;
    }
}