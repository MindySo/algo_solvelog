import java.util.*;
class Solution {
    public double[] solution(int k, int[][] ranges) {
        List<Double> list = new ArrayList();
        while(k > 1){
            list.add((double)k);
            if(k % 2 == 0){
                k /= 2;
            }else{
                k = k * 3 + 1;
            }
        }
        list.add(1d);

        int n = list.size() - 1;
        
        double[] size = new double[n];
        for(int i = 0; i < n; i++){
            size[i] = (list.get(i) + list.get(i + 1)) / 2;
        }
        
        double[] answer = new double[ranges.length];
        for(int idx = 0; idx < ranges.length; idx++){
            int start = ranges[idx][0];
            int end = n + ranges[idx][1];
            
            if(start > end) {
                answer[idx] = -1;
                continue;
            }
                
            double sum = 0;
            for(int i = start; i < end; i++){
                sum += size[i];
            }
            answer[idx] = sum;
        }
        
        return answer;
    }
}