import java.io.*;
import java.util.Arrays;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();
        int[] x = new int[N], y = new int[N];
        for (int i = 0; i < N; i++) {
            x[i] = read();
            y[i] = read();
        }
        Arrays.sort(x);
        Arrays.sort(y);
        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.abs(x[i] - x[N / 2]) + Math.abs(y[i] - y[N / 2]);
        }
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read();
        while (n <= ' ') {
            n = System.in.read();
        }
        n &= 15;

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