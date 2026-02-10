import java.util.*;

class Solution {
    public boolean solution(String[] phone_book) {
        Arrays.sort(phone_book);
        Set<String> set = new HashSet();
        
        String str;
        for(String number : phone_book){
            str = "";
            for(char n : number.toCharArray()){
                str += n - '0';
                if(set.contains(str)) return false;
            }
            set.add(number);
        }
            
        return true;
    }
}