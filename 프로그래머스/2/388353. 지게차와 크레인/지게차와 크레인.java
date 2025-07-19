import java.util.*;

class Solution {
    static String[] storage;
    static String[] requests;

    static int N, M;
    static char[][] containers;
    static int[][] state;
    static Map<Character, List<Integer>> ctMap;
    static Set<Integer> outSet;
    static int answer;

    static int[] dr = {-1, 0, 0, 1};
    static int[] dc = {0, -1, 1, 0};

    public int solution(String[] storage, String[] requests) {
        this.storage = storage;
        this.requests = requests;

        init();
        processRequests();

        return answer;
    }

    static void init() {
        N = storage.length;
        M = storage[0].length();
        containers = new char[N][M];
        state = new int[N][M];
        ctMap = new HashMap<>();
        outSet = new HashSet<>();
        answer = N * M;

        // 알파벳별 위치 기록
        for (char c = 'A'; c <= 'Z'; c++) ctMap.put(c, new ArrayList<>());

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char ch = storage[i].charAt(j);
                containers[i][j] = ch;
                ctMap.get(ch).add(i * M + j);
                state[i][j] = 0; // 초기엔 내부
            }
        }

        // 테두리 영역은 2로 표시
        for (int i = 0; i < N; i++) {
            state[i][0] = 2;
            state[i][M - 1] = 2;
        }
        for (int j = 0; j < M; j++) {
            state[0][j] = 2;
            state[N - 1][j] = 2;
        }
    }

    static void processRequests() {
        for (String req : requests) {
            char type = req.charAt(0);
            List<Integer> positions = ctMap.getOrDefault(type, Collections.emptyList());

            for (int pos : positions) {
                int r = pos / M;
                int c = pos % M;

                if (state[r][c] == 2) {
                    outSet.add(pos); // 테두리 컨테이너는 외부화 대상
                } else if (req.length() > 1 && state[r][c] == 0) {
                    state[r][c] = 3; // 내부 빈공간으로 만들기
                    answer--;       // 컨테이너 사라짐
                }
            }

            propagateOutside(); // 외부화 처리
        }
    }

    static void propagateOutside() {
        while (!outSet.isEmpty()) {
            Set<Integer> nextOut = new HashSet<>();

            for (int pos : outSet) {
                int r = pos / M;
                int c = pos % M;

                if (state[r][c] == 1) continue; // 이미 외부화된 컨테이너

                if (state[r][c] != 3) {
                    answer--; // 컨테이너 사라짐
                }

                state[r][c] = 1; // 외부화

                // 주변 탐색
                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= N || nc < 0 || nc >= M) continue;

                    if (state[nr][nc] == 3) {
                        nextOut.add(nr * M + nc); // 내부 빈공간이 외부와 연결됨
                    } else if (state[nr][nc] == 0) {
                        state[nr][nc] = 2; // 내부 컨테이너지만 외부 인접한 테두리로 마킹
                    }
                }
            }

            outSet = nextOut;
        }
    }
}
