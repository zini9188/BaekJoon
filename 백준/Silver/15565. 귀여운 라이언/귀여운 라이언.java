import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static int[] d;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        int r = 0, ans = N + 1, c = 0;

        for (int l = 1; l <= N; l++) {
            if (d[l - 1] == 1) {
                c--;
            }

            while (r + 1 <= N && c < K) {
                if (d[++r] == 1) {
                    c++;
                }
            }

            if (c == K) {
                ans = Math.min(ans, r - l + 1);
            }
        }

        if (ans == N + 1) {
            ans = -1;
        }

        sb.append(ans);
    }

    private static void input() throws IOException {
        N = read();
        K = read();

        d = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            d[i] = read();
        }
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}