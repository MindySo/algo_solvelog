class Solution {
    public int[] solution(int brown, int yellow) {
        int lSum = (brown - 4) / 2;
        int r = lSum - 1;
        int c = 1;
        while(r * c != yellow){
            r--;
            c++;
        }
        
        return new int[] {r + 2, c + 2};
    }
}