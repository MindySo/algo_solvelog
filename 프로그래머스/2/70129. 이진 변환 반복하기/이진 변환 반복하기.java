class Solution {
    public int[] solution(String s) {
        int removed = 0;
        int tried = 0;
        
        while(s.length() > 1){
            int num = 0;
            for(int i = 0; i < s.length(); i++){
                if(s.charAt(i) == '1') num++;
            }
            removed += (s.length() - num);
            tried++;
            s = convert(num, "");
        }
        
        return new int[] {tried, removed};
    }
    
    public String convert(int num, String str){
        if(num == 0) return str;
        
        if(num % 2 == 1){
            str = '1' + str;
        }else{
            str = '0' + str;
        }
        return convert(num / 2, str);
    }
}