import java.util.*;

class Solution {
    static int N;
    static int[] apeach;
    static int[] lion;
    static int[] answer;
    static int max;
    public int[] solution(int n, int[] info) {
        N = n;
        apeach = info;
        lion = new int[11];
        answer = new int[11];
        max = 0;
        
        for(int i = 0; i < 10; i++){
            DFS(i, 0);
        }
        
        if(max == 0){
            answer = new int[] {-1};
        }
        
        return answer;
    }
    
    public void DFS(int idx, int cnt){
        if(cnt == N){
            int apeachTotal = 0;
            int lionTotal = 0;
            int low = 11;
            for(int i = 0; i < 11; i++){
                if(apeach[i] == 0 && lion[i] == 0) continue;
                
                if(apeach[i] >= lion[i]){
                    apeachTotal += (10 - i);
                }else{
                    lionTotal += (10 - i);
                }
            }
            
            int gap = lionTotal - apeachTotal;
            if(gap > max){
                max = gap;
                answer = Arrays.copyOf(lion, 11);
            }else if(gap == max){
                for(int i = 10; i >= 0; i--){
                    if(lion[i] > answer[i]){
                        max = gap;
                        answer = Arrays.copyOf(lion, 11);
                    }else if(lion[i] < answer[i]){
                        break;
                    }
                }
            }
            return;
        }

        lion[idx]++;
        for(int i = idx; i < 11 ; i++){
            DFS(i, cnt + 1);
        }
        lion[idx]--;
    }
}