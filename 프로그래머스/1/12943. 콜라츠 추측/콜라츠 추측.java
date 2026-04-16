class Solution {
    public int solution(int num) {
        if(num == 1) return 0;
        long n = num;
        
        int cnt = 0;
        while(cnt < 500 && n != 1){
            if(n % 2 == 0) n /= 2;
            else n = n * 3 + 1;
            cnt++;
        }
        
        return cnt < 500 ? cnt : -1;
    }
}