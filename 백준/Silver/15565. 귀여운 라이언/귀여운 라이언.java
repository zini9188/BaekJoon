import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N;
    private static int K;
    private static int[] dolls;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        int right = 0, ans = N + 1, cnt = 0;

        for (int s = 1; s <= N; s++) {
            if (dolls[s - 1] == 1) {
                cnt--;
            }

            while (right + 1 <= N && cnt < K) {
                if (dolls[++right] == 1) {
                    cnt++;
                }
            }

            if (cnt == K) {
                ans = Math.min(ans, right - s + 1);
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

        dolls = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            dolls[i] = read();
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