import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();

        int[] score = new int[N];
        for (int i = 0; i < N; i++) {
            score[i] = read();
        }

        int ans = 0;
        for (int i = N - 2; i >= 0; i--) {
            if (score[i] >= score[i + 1]) {
                int n = score[i + 1] - 1;
                ans += score[i] - n;
                score[i] = n;
            }
        }

        sb.append(ans);
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