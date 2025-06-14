import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static LinkedList<Integer>[][] tree;
    static int[][] nutri;
    static int[][] S2D2;

    // 8방향
    static final int[] dr = {-1,-1,-1, 0,0, 1,1,1};
    static final int[] dc = {-1, 0, 1,-1,1,-1,0,1};

    public static void main(String[] args) throws IOException {
        input();

        for (int year = 0; year < K; year++) {
            springAndSummer();
            autumn();
            winter();
        }

        System.out.println(countTrees());
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        tree  = new LinkedList[N + 1][N + 1];
        nutri = new int[N + 1][N + 1];
        S2D2  = new int[N + 1][N + 1];

        for (int r = 1; r <= N; r++) {
            Arrays.fill(nutri[r], 5);
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                S2D2[r][c] = Integer.parseInt(st.nextToken());
                tree[r][c] = new LinkedList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());
            tree[r][c].add(age);
        }
    }

    static Deque<int[]> breed = new ArrayDeque<>();

    static void springAndSummer() {
        breed.clear();
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (tree[r][c].isEmpty()) continue;

                Collections.sort(tree[r][c]);

                LinkedList<Integer> alive = new LinkedList<>();
                int deadNutri = 0;

                for (int age : tree[r][c]) {
                    if (nutri[r][c] >= age) {
                        nutri[r][c] -= age;
                        ++age;
                        alive.add(age);

                        if (age % 5 == 0) {
                          breed.add(new int[]{r, c});
                        }  
                    } else {
                        deadNutri += age / 2;
                    }
                }

                nutri[r][c] += deadNutri; 
                tree[r][c] = alive;
            }
        }
    }

    static void autumn() {
        while (!breed.isEmpty()) {
            int[] cur = breed.poll();
            int r = cur[0], c = cur[1];

            for (int d = 0; d < 8; d++) {
                int nr = r + dr[d], nc = c + dc[d];
                if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
                tree[nr][nc].addFirst(1);
            }
        }
    }

    static void winter() {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                nutri[r][c] += S2D2[r][c];
            }
        }
    }

    static int countTrees() {
        int cnt = 0;
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                cnt += tree[r][c].size();
            }
        }
        return cnt;
    }
}
