import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static final int INF = 4000 * 100000 * 2 + 1;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken()) * 2;

            graph.get(a).add(new Node(b, d));
            graph.get(b).add(new Node(a, d));
        }

        int[] fDist = fox();
        int[][] wDist = wolf();

        int ans = 0;
        for (int i = 1; i <= N; i++) {
            if (fDist[i] < Math.min(wDist[0][i], wDist[1][i])) {
                ans++;
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int[][] wolf() {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0, 0));
        int[][] dist = new int[2][N + 1];
        for (int i = 0; i < 2; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][1] = 0;

        while (!pq.isEmpty()) {
            Node wolf = pq.poll();

            if (dist[wolf.status][wolf.vertex] < wolf.weight) {
                continue;
            }

            for (Node next : graph.get(wolf.vertex)) {
                int ns = 1 - wolf.status;
                int nw = dist[wolf.status][wolf.vertex];
                if (ns == 0) {
                    nw += next.weight * 2;
                } else {
                    nw += next.weight / 2;
                }

                if (nw < dist[ns][next.vertex]) {
                    dist[ns][next.vertex] = nw;
                    pq.add(new Node(next.vertex, nw, ns));
                }
            }
        }
        return dist;
    }

    private static int[] fox() {
        Queue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(1, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Node fox = pq.poll();

            if (fox.weight > dist[fox.vertex]) {
                continue;
            }

            for (Node next : graph.get(fox.vertex)) {
                if (dist[fox.vertex] + next.weight < dist[next.vertex]) {
                    dist[next.vertex] = dist[fox.vertex] + next.weight;
                    pq.add(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }
        return dist;
    }

    static class Node implements Comparable<Node> {

        int vertex, weight, status;

        public Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }

        public Node(int vertex, int weight, int status) {
            this.vertex = vertex;
            this.weight = weight;
            this.status = status;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}