import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine().trim());
        int[] dice = new int[6];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        // N == 1 예외 처리
        if (N == 1) {
            int max = 0, sum = 0;
            for (int v : dice) {
                sum += v;
                max = Math.max(max, v);
            }
            System.out.println(sum - max);
            return;
        }

        int oneMin = Integer.MAX_VALUE;
        for (int v : dice) {
            oneMin = Math.min(oneMin, v);
        }

        int twoMin = Integer.MAX_VALUE;
        int[][] twoComb = {
            {0, 1}, {0, 2}, {0, 3}, {0, 4},
            {1, 2}, {1, 3}, {1, 5},
            {2, 4}, {2, 5},
            {3, 4}, {3, 5},
            {4, 5}
        };
        for (int[] pair : twoComb) {
            twoMin = Math.min(twoMin, dice[pair[0]] + dice[pair[1]]);
        }

        int threeMin = Integer.MAX_VALUE;
        int[][] threeComb = {
            {3, 4, 0}, {3, 0, 1}, {3, 1, 5}, {3, 5, 4},
            {2, 4, 0}, {2, 0, 1}, {2, 1, 5}, {2, 5, 4}
        };
        for (int[] comb : threeComb) {
            int sum = dice[comb[0]] + dice[comb[1]] + dice[comb[2]];
            threeMin = Math.min(threeMin, sum);
        }

        long countThree = 4;
        long countTwo = 8 * N - 12;
        long countOne = 5 * N * N - 16 * N + 12;

        BigInteger result = BigInteger.ZERO;
        result = result.add(BigInteger.valueOf(threeMin).multiply(BigInteger.valueOf(countThree)));
        result = result.add(BigInteger.valueOf(twoMin).multiply(BigInteger.valueOf(countTwo)));
        result = result.add(BigInteger.valueOf(oneMin).multiply(BigInteger.valueOf(countOne)));

        System.out.println(result);
    }
}
