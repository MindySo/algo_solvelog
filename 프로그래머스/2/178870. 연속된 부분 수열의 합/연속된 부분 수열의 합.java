class Solution {
    public int[] solution(int[] sequence, int k) {
        int size = sequence.length;
        int minLength = size, start = 0, end = 0, length;
        int sum = sequence[0];
        int[] answer = new int[2];
        
        while(end < size){
            if(sum < k){
                end++;
                if(end >= size){
                    break;
                }

                sum += sequence[end];
            }else if(sum > k){
                sum -= sequence[start];
                start++;
            }else if(sum == k){
                length = end - start;
                if(length < minLength){
                    minLength = length;
                    answer[0] = start;
                    answer[1] = end;
                }
                
                end++;
                if(end >= size){
                    break;
                }
                sum += sequence[end];
            }
        }
        
        return answer;
    }
}