import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();

        int[] a1 = new int[N];
        int[] a2 = new int[N];
        for (int i = 0; i < N; i++) {
            a1[i] = read();
        }

        for (int i = 0; i < N; i++) {
            a2[i] = read();
        }

        Queue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            q.add(new Node(a1[i], a2[i]));
        }

        long ans = 0;
        for (int day = 0; day < N; day++) {
            if (!q.isEmpty()) {
                Node node = q.poll();
                ans += node.s1 + ((long) node.s2 * day);
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
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

        int s1, s2;

        public Node(int s1, int s2) {
            this.s1 = s1;
            this.s2 = s2;
        }

        @Override
        public int compareTo(Node o) {
            return s2 - o.s2;
        }
    }
}