class Solution {
    static int answer;
    static int tgt;
    static int[] nums;
    public int solution(int[] numbers, int target) {
        answer = 0;
        tgt = target;
        nums = numbers;
        DFS(0, 0);
        
        return answer;
    }

    public void DFS(int idx, int sum){
        if(idx == nums.length){
            if(sum == tgt) answer++;
            return;
        }
        DFS(idx + 1, sum + nums[idx]);
        DFS(idx + 1, sum - nums[idx]);
    }
}