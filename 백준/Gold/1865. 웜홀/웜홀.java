import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int from, to, cost;

        Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }

    static List<Edge> edges;
    static int[] dist;
    static int INF = 100000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();

            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                edges.add(new Edge(s, e, t));
                edges.add(new Edge(e, s, t));
            }

            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int t = Integer.parseInt(st.nextToken());

                edges.add(new Edge(s, e, -t));
            }

            // 모든 정점과 연결된 노드 0을 추가, 여기서 출발하는 걸로 한번만 탐지
            for (int i = 1; i <= N; i++) {
                edges.add(new Edge(0, i, 0));
            }

            dist = new int[N + 1];
            Arrays.fill(dist, INF);
            dist[0] = 0;

            boolean hasCycle = false;

            for (int i = 0; i < N; i++) {
                for (Edge edge : edges) {
                    if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                        dist[edge.to] = dist[edge.from] + edge.cost;
                    }
                }
            }

            for (Edge edge : edges) {
                if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.cost) {
                    hasCycle = true;
                    break;
                }
            }

            System.out.println(hasCycle ? "YES" : "NO");
        }
    }
}
