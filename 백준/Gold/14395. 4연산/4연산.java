import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static char[] operate = {'*', '+', '-', '/'};

    public static void main(String[] args) throws IOException {
        int s = read();
        int t = read();

        if (s == t) {
            sb.append("0");
        } else {
            sb.append(bfs(s, t));
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String bfs(int s, int t) {
        Set<Long> set = new HashSet<>();

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(s, ""));
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.n == t) {
                return node.str;
            }

            for (int i = 0; i < 4; i++) {
                long nn = calc(node.n, operate[i]);

                if (!set.contains(nn)) {
                    q.add(new Node(nn, node.str + operate[i]));
                    set.add(nn);
                }
            }
        }
        return "-1";
    }

    private static long calc(long n, char o) {
        if (o == '*') {
            return n * n;
        } else if (o == '+') {
            return n + n;
        } else if (o == '-') {
            return 0;
        } else {
            return 1;
        }
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

    private static class Node {

        long n;
        String str;

        public Node(long n, String str) {
            this.n = n;
            this.str = str;
        }
    }
}