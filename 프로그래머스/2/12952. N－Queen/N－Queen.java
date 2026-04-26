import java.util.*;
class Solution {
    static int N;
    static int answer;
    static boolean[] availC;
    static boolean[] unavail;

    public int solution(int n) {
        N = n;
        availC = new boolean[n];
        unavail = new boolean[n * n];
        
        Arrays.fill(availC, true);
        answer = 0;
        DFS(0);
        
        return answer;
    }
    
    public void DFS(int currR){
        if(currR == N) {
            answer++;
            return;
        }
        
        Map<Integer, Boolean> bt = new HashMap();
        
        nextC:
        for(int c = 0; c < N; c++){
            int rc = currR * N + c;
            if(!availC[c] || unavail[rc]) continue;
            availC[c] = false;

            int nextRC = rc;
            for(int nextC = c - 1; nextC >= 0; nextC--){
                nextRC = nextRC - 1 + N;
                if(nextRC >= N * N) break;
                bt.put(nextRC, unavail[nextRC]);
                unavail[nextRC] = true;
            }

            nextRC = rc;
            for(int nextC = c + 1; nextC < N; nextC++){
                nextRC = nextRC + 1 + N;
                if(nextRC >= N * N) break;
                bt.put(nextRC, unavail[nextRC]);
                unavail[nextRC] = true;
            }

            DFS(currR + 1);

            for(int btRC : bt.keySet()){
                unavail[btRC] = bt.get(btRC);
            }
            bt.clear();

            availC[c] = true;
        }
            
    }
    
}