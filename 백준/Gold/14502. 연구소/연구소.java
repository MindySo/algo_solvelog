import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] lab;
    static List<int[]> empties = new ArrayList<>();
    static List<int[]> viruses = new ArrayList<>();
    static int emptyCount;
    static final int[] dr = { -1, 1, 0, 0 };
    static final int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        lab = new int[N][M];

        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                lab[r][c] = Integer.parseInt(st.nextToken());
                if (lab[r][c] == 0) {
                    empties.add(new int[]{r, c});
                } else if (lab[r][c] == 2) {
                    viruses.add(new int[]{r, c});
                }
            }
        }
        emptyCount = empties.size();

        System.out.println(solve());
    }

    static int solve() {
        int maxSafe = 0;
        int eSize = empties.size();

        for (int i = 0; i < eSize; i++) {
            for (int j = i + 1; j < eSize; j++) {
                for (int k = j + 1; k < eSize; k++) {
                    int[][] temp = copyLab();

                    // 벽 세우기
                    int[] a = empties.get(i);
                    int[] b = empties.get(j);
                    int[] c = empties.get(k);
                    temp[a[0]][a[1]] = 1;
                    temp[b[0]][b[1]] = 1;
                    temp[c[0]][c[1]] = 1;

                    int infected = spreadVirus(temp);
                    int safe = (emptyCount - 3) - infected;
                    if (safe > maxSafe) maxSafe = safe;
                }
            }
        }
        return maxSafe;
    }

    static int spreadVirus(int[][] map) {
        boolean[][] visited = new boolean[N][M];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        for (int[] v : viruses) {
            q.offer(new int[]{v[0], v[1]});
            visited[v[0]][v[1]] = true;
        }

        int infected = 0;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int r = cur[0], c = cur[1];

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;
                if (visited[nr][nc]) continue;
                if (map[nr][nc] != 0) continue;
                visited[nr][nc] = true;
                map[nr][nc] = 2;
                infected++;
                q.offer(new int[]{nr, nc});
            }
        }
        return infected;
    }

    static int[][] copyLab() {
        int[][] copy = new int[N][M];
        for (int i = 0; i < N; i++) {
            System.arraycopy(lab[i], 0, copy[i], 0, M);
        }
        return copy;
    }
}
