class Solution {
    public int solution(int n, long l, long r) {
        return countOnes(n, r) - countOnes(n, l - 1);
    }

    public int countOnes(int n, long x) {
        if (x <= 0) return 0;
        if (x >= (long) Math.pow(5, n)) return totalOnes(n);

        long blockSize = (long) Math.pow(5, n - 1);
        int blockIdx = (int) (x / blockSize);
        long remainder = x % blockSize;

        int ones = 0;
        for (int i = 0; i < blockIdx; i++) {
            if (i != 2) ones += totalOnes(n - 1);
        }

        if (blockIdx != 2) {
            ones += countOnes(n - 1, remainder);
        }

        return ones;
    }

    public int totalOnes(int n) {
        if (n == 0) return 1;
        return (int) Math.pow(4, n);
    }
}