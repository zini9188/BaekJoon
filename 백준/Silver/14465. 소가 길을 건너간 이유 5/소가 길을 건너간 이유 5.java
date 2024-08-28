import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read(), K = read(), B = read();

        int[] arr = new int[N + 1];
        for (int i = 0; i < B; i++) {
            arr[read()] = 1;
        }

        int cnt = 0;
        for (int i = 1; i <= K; i++) {
            cnt += arr[i];
        }

        int ans = cnt;
        for (int i = K + 1, j = 1; i <= N; i++, j++) {
            cnt += arr[i];
            cnt -= arr[j];
            ans = Math.min(ans, cnt);
        }

        sb.append(ans);
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