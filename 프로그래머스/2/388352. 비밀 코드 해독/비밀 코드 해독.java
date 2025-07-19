import java.util.*;
class Solution {
    static int N;
    static int[][] q;
    static int[] ans;
    static boolean[] code;
    static int answer;
    public int solution(int n, int[][] q, int[] ans) {
        N = n;
        this.q = q;
        this.ans = ans;
        code = new boolean[n + 1];
        answer = 0;
        
        setCode(1, 0);
        
        return answer;
    }
    
    static void setCode(int idx, int cnt){
        if(cnt >= 5){
            if(checkPossibility()){
                answer++;
            }
            return;
        }
        
        if(idx > N){
            return;
        }
        
        code[idx] = true;
        setCode(idx + 1, cnt + 1);
        
        code[idx] = false;
        setCode(idx + 1, cnt);
    }
    
    static boolean checkPossibility(){
        int cnt;
        for(int i = 0 ; i < q.length ; i++){
            cnt = 0;
            for(int idx : q[i]){
                if(code[idx]){
                    cnt++;
                }
            }
            
            if(cnt != ans[i]){
                return false;
            }
        }
        return true;
    }
}