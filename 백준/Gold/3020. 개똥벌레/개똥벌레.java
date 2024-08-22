import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();
        int H = read();

        int[] prefixSum = new int[H + 2];
        for (int i = 1; i <= N; i++) {
            int n = read();

            if (i % 2 == 1) {
                prefixSum[1]++;
                prefixSum[n + 1]--;
            } else {
                prefixSum[H - n + 1]++;
                prefixSum[H + 1]--;
            }
        }

        for (int i = 1; i <= H; i++) {
            prefixSum[i] += prefixSum[i - 1];
        }

        int min = Integer.MAX_VALUE;
        for (int i = 1; i <= H; i++) {
            if (min > prefixSum[i]) {
                min = prefixSum[i];
            }
        }

        int cnt = 0;
        for (int i = 1; i <= H; i++) {
            if (prefixSum[i] == min) {
                cnt++;
            }
        }

        sb.append(min).append(" ").append(cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}