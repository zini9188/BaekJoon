import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();

        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        int cycle = 0;
        for (int i = 0; i < M; i++) {
            int a = readInt();
            int b = readInt();

            if (find(a) == find(b)) {
                cycle++;
                continue;
            }

            union(a, b);
        }

        for (int i = 1; i <= N; i++) {
            if (parent[i] == i) {
                cycle++;
            }
        }

        sb.append(cycle - 1).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a < b) {
            parent[b] = a;
        } else {
            parent[a] = b;
        }
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
}