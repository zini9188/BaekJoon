import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            graph.get(a).add(new Node(b, c));
            graph.get(b).add(new Node(a, c));
        }

        int[] fromStart = dijkstra(V, graph, 1);
        int[] fromSave = dijkstra(V, graph, P);

        if (fromStart[V] == (fromSave[1] + fromSave[V])) {
            sb.append("SAVE HIM");
        } else {
            sb.append("GOOD BYE");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int[] dijkstra(int V, List<List<Node>> graph, int start) {
        int[] dist = new int[V + 1];
        Arrays.fill(dist, 5000 * 10000 + 1);
        dist[start] = 0;
        Queue<Node> q = new PriorityQueue<>();
        q.add(new Node(start, 0));

        boolean[] visited = new boolean[V + 1];
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (visited[node.v]) {
                continue;
            }
            visited[node.v] = true;

            for (Node next : graph.get(node.v)) {
                if (dist[next.v] > node.w + next.w) {
                    dist[next.v] = node.w + next.w;
                    q.add(new Node(next.v, dist[next.v]));
                }
            }
        }

        return dist;
    }

    private static class Node implements Comparable<Node> {

        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return w - o.w;
        }
    }
}