class Solution {
    public int solution(int n) {
        int count = Integer.bitCount(n);
        int next = n + 1;
        while (Integer.bitCount(next) != count) next++;
        return next;
    }
}