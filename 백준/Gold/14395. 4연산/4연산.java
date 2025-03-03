import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int s = read();
        int t = read();

        if (s == t) {
            sb.append("0");
        } else {
            sb.append(bfs((long)s, t));
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String bfs(long s, int t) {
        Set<Long> set = new HashSet<>();
        set.add(s);

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(s, ""));
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.n == t) {
                return node.str;
            }

            long nn;
            if (node.n <= 100000) {
                nn = node.n * node.n;
                if (!set.contains(nn)) {
                    q.add(new Node(nn, node.str + '*'));
                    set.add(nn);
                }
            }

            if (node.n <= 500000000) {
                nn = node.n + node.n;
                if (!set.contains(nn)) {
                    q.add(new Node(nn, node.str + '+'));
                    set.add(nn);
                }
            }

            if (!set.contains(0L)) {
                q.add(new Node(0L, node.str + '-'));
                set.add(0L);
            }

            if (node.n != 0) {
                if (!set.contains(1L)) {
                    q.add(new Node(1L, node.str + '/'));
                    set.add(1L);
                }                
            }
        }
        return "-1";
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