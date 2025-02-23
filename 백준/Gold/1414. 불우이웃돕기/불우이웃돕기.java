import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        int N = read();

        int total = 0;
        char[][] connections = new char[N][N];
        Queue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            connections[i] = br.readLine().toCharArray();

            for (int j = 0; j < N; j++) {
                char c = connections[i][j];

                if (c == '0') {
                    continue;
                }

                int w = 0;
                if (Character.isUpperCase(c)) {
                    w = c - 'A' + 27;
                } else if (Character.isLowerCase(c)) {
                    w = c - 'a' + 1;
                }

                total += w;
                pq.add(new Node(i, j, w));
            }
        }

        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }

        int using = 0;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            int from = node.from;
            int to = node.to;

            if (union(from, to)) {
                using += node.w;
            }
        }

        Set<Integer> collect = Arrays.stream(parent).mapToObj(Main::find)
                .collect(Collectors.toSet());

        if (collect.size() == 1) {
            sb.append(total - using);
        } else {
            sb.append("-1");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return false;
        }

        parent[b] = a;
        return true;
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = find(parent[x]);
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return negative ? -n : n;
    }

    private static class Node implements Comparable<Node> {

        int from, to, w;

        public Node(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }

        @Override
        public int compareTo(Node o) {
            return w - o.w;
        }
    }
}