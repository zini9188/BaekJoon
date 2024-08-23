import java.io.*;
import java.util.Arrays;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();
        int M = read();

        int[] arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = read();
        }

        int[] prefixSum = new int[N + 2];
        for (int i = 0; i < M; i++) {
            int a = read();
            int b = read();
            int k = read();

            prefixSum[a] += k;
            prefixSum[b + 1] -= k;
        }

        for (int i = 1; i <= N; i++) {
            prefixSum[i] += prefixSum[i - 1];
            sb.append(arr[i] + prefixSum[i]).append(" ");
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