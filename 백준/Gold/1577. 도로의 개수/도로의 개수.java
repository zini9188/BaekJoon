import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();
        int M = read();

        long[][] road = new long[N + 2][M + 2];

        int K = read();
        HashMap<Data, Boolean> hashMap = new HashMap<>();
        for (int i = 0; i < K; i++) {
            int a = read() + 1;
            int b = read() + 1;
            int c = read() + 1;
            int d = read() + 1;
            hashMap.put(new Data(a, b, c, d), true);
            hashMap.put(new Data(c, d, a, b), true);
        }

        road[1][1] = 1;
        for (int x = 1; x <= N + 1; x++) {
            for (int y = 1; y <= M + 1; y++) {
                if (x == 1 && y == 1) {
                    continue;
                }
                long left = road[x - 1][y], right = road[x][y - 1];
                if (hashMap.containsKey(new Data(x - 1, y, x, y))) {
                    left = 0;
                }

                if (hashMap.containsKey(new Data(x, y - 1, x, y))) {
                    right = 0;
                }

                road[x][y] = left + right;
            }
        }

        sb.append(road[N + 1][M + 1]);
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

    private static class Data {

        int a, b, c, d;

        public Data(int a, int b, int c, int d) {
            this.a = a;
            this.b = b;
            this.c = c;
            this.d = d;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Data data = (Data) o;
            return a == data.a && b == data.b && c == data.c && d == data.d;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b, c, d);
        }
    }
}