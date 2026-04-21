class Solution {
    public int[] solution(int[] arr) {
        if(arr.length == 1) return new int[] {-1};
        
        int[] answer = new int[arr.length - 1];
        int idx = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] < min){
                idx = i;
                min = arr[i];
            }
        }
        
        for(int i = 0; i < idx; i++){
            answer[i] = arr[i];
        }
    
        for(int i = idx + 1; i < arr.length; i++){
            answer[i - 1] = arr[i];
        }
        
        return answer;
    }
}