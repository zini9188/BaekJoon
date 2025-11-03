import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int K = readInt();

        Queue<Pair> queue = new PriorityQueue<>();
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            int n = readInt();
            queue.add(new Pair(n, 1));
            set.add(n);
        }

        long sum = 0;
        int[] dx = {-1, 1};
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            for (int i = 0; i < 2; i++) {
                int nx = pair.n + dx[i];
                if(!set.contains(nx)) {
                    set.add(nx);
                    sum += pair.d;
                    K--;
                    queue.add(new Pair(nx, pair.d + 1));
                }
            }

            if (K == 0) {
                break;
            }
        }

        sb.append(sum);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int readInt() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        if (c == 13) {
            System.in.read();
        }

        return negative ? -n : n;
    }

    private static class Pair implements Comparable<Pair> {
        int n;
        int d;

        Pair(int n, int d) {
            this.n = n;
            this.d = d;
        }

        @Override
        public int compareTo(Pair o) {
            return d - o.d;
        }
    }
}