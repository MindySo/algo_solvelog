class Solution {
    boolean solution(String s) {
        int p = 0, y = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            if(c == 'P' || c == 'p') p++;
            else if(c == 'Y' || c == 'y') y++;
        }

        return p == y ? true : false;
    }
}