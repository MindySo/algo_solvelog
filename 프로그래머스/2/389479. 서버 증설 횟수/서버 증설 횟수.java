import java.util.*;
import java.io.*;
class Solution {
    public int solution(int[] players, int m, int k) {
        int[] servers = new int[24];
        int need, answer = 0;
        for(int i = 0; i < 24; i++){
            need = players[i] / m;
            if(servers[i] >= need){
                continue;
            }else{
                need -= servers[i];
                answer += need;
                for(int t = 0; t < k; t++){
                    if(i + t < 24){
                        servers[i + t] += need;
                    }
                }
            }
        }
        return answer;
    }
}