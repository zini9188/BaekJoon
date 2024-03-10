import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static List<List<Node>> graph;
    private static final int INF = 1000 * 50000 + 1;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        sb.append(dijkstra());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int dijkstra() {
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(1, 0));
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[1] = 0;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (visited[cur.position]) {
                continue;
            }
            visited[cur.position] = true;
            dist[cur.position] = cur.dist;

            for (Node next : graph.get(cur.position)) {
                if (!visited[next.position] && dist[next.position] > cur.position + next.dist) {
                    q.add(new Node(next.position, dist[cur.position] + next.dist));
                }
            }
        }

        return dist[N] == INF ? -1 : dist[N];
    }


    static class Node implements Comparable<Node> {

        int position, dist;

        public Node(int position, int dist) {
            this.position = position;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return dist - o.dist;
        }
    }
}