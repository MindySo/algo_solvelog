class Solution {
    public int solution(int[] a) {
        int[] leftMin = new int[a.length];
        int[] rightMin = new int[a.length];
        
        int lmin = Integer.MAX_VALUE, rmin = Integer.MAX_VALUE;
        for(int i = 0; i < a.length; i++){
            leftMin[i] = lmin;
            rightMin[a.length - 1 - i] = rmin;
            lmin = Math.min(lmin, a[i]);
            rmin = Math.min(rmin, a[a.length - 1  - i]);
        }
        
        int answer = 2;
        for(int i = 1; i < a.length - 1; i++){
            if(a[i] < leftMin[i] || a[i] < rightMin[i]) answer++;
        }
        
        return answer;
    }
}