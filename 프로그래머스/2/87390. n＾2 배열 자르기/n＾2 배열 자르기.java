class Solution {
    public int[] solution(int n, long left, long right) {
        int leftR = (int)(left / n);
        int leftC = (int)(left % n);
        int rightR = (int)(right / n);
        int rightC = (int)(right % n);
        
        int length = (int)(right - left + 1);
        int[] answer = new int[length];
        int idx = 0;
        
        if(leftR == rightR){
            while(leftC <= rightC && leftC <= leftR){
                answer[idx++] = leftR + 1;
                leftC++;
            }

            while(leftC <= rightC){
                answer[idx++] = leftC + 1;
                leftC++;
            }
            return answer;
        }
        
        while(leftC <= leftR){
            answer[idx++] = leftR + 1;
            leftC++;
        }
        
        while(leftC < n){
            answer[idx++] = leftC + 1;
            leftC++;
        }
        
        for(int r = leftR + 1; r < rightR; r++){
            for(int c = 0; c <= r; c++){
                answer[idx++] = r + 1;
            }
            
            for(int c = r + 1; c < n; c++){
                answer[idx++] = c + 1;
            }
        }
        
        int c = 0;
        while(c <= rightC && c <= rightR){
            answer[idx++] = rightR + 1;
            c++;
        }
        
        while(c <= rightC){
            answer[idx++] = c + 1;
            c++;
        }
        
        return answer;
    }
}