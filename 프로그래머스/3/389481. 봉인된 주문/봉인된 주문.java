import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        List<String>[] banList = new List[12];
        for(int i = 1; i <= 11; i++){
            banList[i] = new ArrayList();
        }
        
        for(String ban : bans){
            banList[ban.length()].add(ban);
        }
        
        int len = 1;
        long size = 0;
        while(n + banList[len].size() > size + Math.pow(26, len)){
            n += banList[len].size();
            size += Math.pow(26, len++);
        }
        
        Collections.sort(banList[len]);
        
        n -= size;
        int pos = len - 1;
        String answer = "";
        
        while(pos > 0){
            long max = (long)Math.pow(26, pos--);
            int idx = (int)(n / max);
            long remain = n % max;
            
            if(remain == 0){
                idx--;
                remain = max;
            }
            
            char c = 'a';
            c += idx;
            answer += c;
            
            n = remain;
        }
        char c = 'a';
        int idx = (int)n - 1;
        c += idx;
        answer += c;
        
        for(String ban : banList[len]){
            if(ban.compareTo(answer) > 0) break;
            answer = next(answer);
        }
        
        return answer;
    }
    
    public String next(String str){
        int idx = str.length() - 1;
        char[] arr = str.toCharArray();
        
        while(true){
            if(arr[idx] < 'z'){
                arr[idx]++;
                break;
            }
            arr[idx--] = 'a';
        }
        
        str = "";
        for(char c : arr) str += c;
        
        return str;
    }
    
}