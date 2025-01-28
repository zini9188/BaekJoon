import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();
        int T = read();
        int G = read();

        sb.append(bfs(N, T, G));

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String bfs(int N, int T, int G) {
        int MAX = 99999;
        boolean[] visited = new boolean[MAX + 1];
        Queue<Pair> q = new ArrayDeque<>();
        q.add(new Pair(N, 0));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int n = p.num;
            int cnt = p.cnt;

            if (cnt > T) {
                continue;
            }

            if (n == G) {
                return String.valueOf(cnt);
            }

            if (n + 1 <= MAX && !visited[n + 1]) {
                visited[n + 1] = true;
                q.add(new Pair(n + 1, cnt + 1));
            }

            if (n * 2 <= MAX) {
                if (n == 0) {
                    continue;
                }

                n = (n * 2);

                int digit = 100000;

                while (n / digit == 0) {
                    digit /= 10;
                }
                n -= digit;

                if (!visited[n]) {
                    visited[n] = true;
                    q.add(new Pair(n, cnt + 1));
                }
            }
        }

        return "ANG";
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

    private static class Pair implements Comparable<Pair> {

        int num, cnt;

        public Pair(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }

        @Override
        public int compareTo(Pair o) {
            return cnt - o.cnt;
        }
    }
}