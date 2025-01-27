import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int T = read();
        for (int i = 0; i < T; i++) {
            solution();
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() throws IOException {
        int N = read(); // 집 개수
        int M = read(); // 훔칠 연속된 집의 개수
        int K = read(); // 방범장치 작동 최소 돈의 양

        long[] town = new long[N * 2 + 1];
        long[] prefixSum = new long[N + M];
        for (int i = 1; i <= N; i++) {
            town[i] = read();
            town[i + N] = town[i];
            prefixSum[i] = prefixSum[i - 1] + town[i];
        }

        for (int i = N; i < N + M; i++) {
            prefixSum[i] = prefixSum[i - 1] + town[i];
        }

        int ans = 0;
        for (int i = M; i < N + M; i++) {
            long money = prefixSum[i] - prefixSum[i - M];
            if (money < K) {
                ans++;
            }
        }

        if (N == M) {
            ans = prefixSum[N] - prefixSum[N - M] >= K ? 0 : 1;
        }
        sb.append(ans).append("\n");
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}