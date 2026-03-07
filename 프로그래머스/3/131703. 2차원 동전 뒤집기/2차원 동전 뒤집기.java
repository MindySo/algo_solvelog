import java.util.*;
class Solution {
    static int n, m;
    static String targetStr;
    static int answer;
    public int solution(int[][] beginning, int[][] target) {
        n = target.length;
        m = target[0].length;
        targetStr = "";
        String startStr = "";
        for(int i = 0; i < n; i++){
            int tSum = 0, sSum = 0;
            for(int j = 0; j < m; j++) {
                startStr += beginning[i][j];
                targetStr += target[i][j];
            }
            startStr += " ";
            targetStr += " ";
        }
        
        answer = Integer.MAX_VALUE;
        RDFS(startStr, 0, 0);
        
        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
    
    public void RDFS(String str, int idx, int tried){
        if(idx == n){
            CDFS(str, 0, tried);
            return;
        } 
        
        RDFS(str, idx + 1, tried);
            
        String[] arr = str.split(" ");
        
        String newStr = "";
        for(char c : arr[idx].toCharArray()) newStr += c == '0' ? 1 : 0;
        arr[idx] = newStr;
            
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++) {
            sb.append(arr[i]);
            sb.append(" ");
        }
        
        RDFS(sb.toString(), idx + 1, tried + 1);
    }
    
    public void CDFS(String str, int idx, int tried){
        if(str.equals(targetStr)) answer = Math.min(answer, tried);
        if(idx == m) return;
        
        CDFS(str, idx + 1, tried);
            
        String[] arr = str.split(" ");
        
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(j == idx){
                    sb.append(arr[i].charAt(j) == '0' ? 1 : 0);
                }else{
                    sb.append(arr[i].charAt(j));
                }
            }
           sb.append(" ");
        }
            
        CDFS(sb.toString(), idx + 1, tried + 1);
    }
}