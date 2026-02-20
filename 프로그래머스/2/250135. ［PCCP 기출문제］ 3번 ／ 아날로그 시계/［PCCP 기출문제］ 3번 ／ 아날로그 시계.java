class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int curr = h1 * 3600 + m1 * 60 + s1;
        int end = h2 * 3600 + m2 * 60 + s2;
        
        double hDeg = 360d / 12 / 60 / 60;
        double mDeg = 360d / 60 / 60;
        double sDeg = 360d / 60;
        
        double h = hDeg * curr;
        double m = mDeg * curr;
        double s = sDeg * curr;
        
        int cnt = 0;
        int cntH = (int)((s - h) / 360);
        int cntM = (int)((s - m) / 360);
        if((s - h) % 360 == 0) cnt++; 
        if((s - m) % 360 == 0) cnt++; 
        
        while(curr <= end){
            if((int)((s - h) / 360) > cntH) {
                cnt++;
                cntH++;
            }
            
            if((int)((s - m) / 360) > cntM) {
                cnt++;
                cntM++;
            }
            
            if(curr == 0 || curr == 43200) cnt--;
            
            curr++;
            h = hDeg * curr;
            m = mDeg * curr;
            s = sDeg * curr;
        }
        
        return cnt;
    }
}