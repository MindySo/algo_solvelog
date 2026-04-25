import java.util.*;
class Solution {
    public int[] solution(long begin, long end) {
        int[] answer = new int[(int)(end - begin + 1)];
        for(long i = begin; i <= end; i++){
            int idx = (int)(i - begin);
            answer[idx] = 1;
            List<Integer> list = new ArrayList();
            
            long num = 2;
            while(num * num <= i){
                if(i % num == 0){
                    list.add((int)num);
                    if(i / num <= 10_000_000){
                        answer[idx] = (int)(i / num);
                        break;
                    }
                }
                num++;
            }
            
            if(answer[idx] == 1){
                if(!list.isEmpty()) answer[idx] = list.get(list.size() - 1);
            }
            
        }

        if(begin == 1) answer[0] = 0;
        
        return answer;
    }
}