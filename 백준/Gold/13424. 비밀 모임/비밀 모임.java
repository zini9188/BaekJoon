import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static final int MAX = 987654321;
    private static StringTokenizer st;
    private static int N, M;
    private static List<List<Node>> graph;
    private static Map<Integer, int[]> distMap;

    public static void main(String[] args) throws IOException {
        int T = read();

        for (int i = 0; i < T; i++) {
            input();
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void input() throws IOException {
        N = read();
        M = read();

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            int a = read();
            int b = read();
            int c = read();
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        distMap = new HashMap<>();
        int K = read();
        for (int i = 1; i <= K; i++) {
            int currentRoom = read();
            dijkstra(currentRoom);
        }

        int ans = Integer.MAX_VALUE;
        int idx = N;
        for (int i = N; i > 0; i--) {
            int res = 0;

            for (Integer key : distMap.keySet()) {
                res += distMap.get(key)[i];
            }

            if (ans >= res) {
                ans = res;
                idx = i;
            }
        }

        sb.append(idx).append("\n");
    }

    private static void dijkstra(int room) {
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(room, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, MAX);
        dist[room] = 0;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (Node next : graph.get(node.to)) {
                int nd = dist[node.to] + next.weight;
                if (dist[next.to] > nd) {
                    dist[next.to] = nd;
                    q.add(new Node(next.to, nd));
                }
            }
        }

        distMap.put(room, dist);
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    public static class Node implements Comparable<Node> {

        int to, weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }
    }
}