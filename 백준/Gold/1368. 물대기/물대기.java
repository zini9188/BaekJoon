import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int N = read();

        int[] arr = new int[N];
        Arrays.fill(arr, Integer.MAX_VALUE - 100001);

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        Queue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            int n = read();
            q.add(new Node(i, n));
            arr[i] = n;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (i == j) {
                    read();
                    continue;
                }

                int n = read();
                graph.get(i).add(new Node(j, n));
            }
        }

        boolean[] visited = new boolean[N];
        int ans = 0;
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (visited[node.f]) {
                continue;
            }
            visited[node.f] = true;
            ans += node.w;

            for (Node next : graph.get(node.f)) {
                if (visited[next.f]) {
                    continue;
                }

                if (arr[next.f] > next.w) {
                    arr[next.f] = next.w;
                    q.add(next);
                }
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    private static class Node implements Comparable<Node> {

        int f, w;

        public Node(int f, int w) {
            this.f = f;
            this.w = w;
        }
        
        @Override
        public int compareTo(Node o) {
            return w - o.w;
        }
    }
}