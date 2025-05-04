import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        map = new char[N][N];
        star(0, 0, N, false);

        StringBuilder sb = new StringBuilder();
        for (int i = 0 ; i < N ; i++) {
            for (int j = 0 ; j < N ; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void star(int r, int c, int size, boolean blank) {
        if (blank) {
            for (int i = r ; i < r + size ; i++) {
                for (int j = c ; j < c + size ; j++) {
                    map[i][j] = ' ';
                }
            }
            return;
        }

        if (size == 1) {
            map[r][c] = '*';
            return;
        }

        int newSize = size / 3;
        int count = 0;
        for (int i = 0 ; i < 3 ; i++) {
            for (int j = 0 ; j < 3 ; j++) {
                count++;
                if (count == 5) {
                    star(r + i * newSize, c + j * newSize, newSize, true);
                } else {
                    star(r + i * newSize, c + j * newSize, newSize, false);
                }
            }
        }
    }
}
