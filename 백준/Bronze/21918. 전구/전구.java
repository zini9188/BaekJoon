import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();
        int M = read();
        boolean[] state = new boolean[N + 1];
        for (int i = 0; i < N; i++) {
            state[i + 1] = read() == 1;
        }

        for (int m = 0; m < M; m++) {
            int a = read();
            if (a == 1) {
                int i = read();
                int x = read();
                state[i] = x == 1;
            } else {
                int l = read();
                int r = read();
                for (int i = l; i <= r; i++) {
                    if (a == 2) {
                        state[i] = !state[i];
                    } else {
                        state[i] = a != 3;
                    }
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            sb.append(state[i] ? "1" : "0").append(" ");
        }
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
}