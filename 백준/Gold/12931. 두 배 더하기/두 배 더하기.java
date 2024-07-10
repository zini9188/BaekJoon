import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] B;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        int cnt = 0;
        while (true) {
            boolean flag = true;
            boolean flag2 = true;
            for (int b : B) {
                if (b != 0) {
                    flag2 = false;
                }

                if (b % 2 == 1) {
                    flag = false;
                    break;
                }
            }

            if (flag2) {
                sb.append(cnt);
                break;
            }

            if (flag) {
                for (int i = 0; i < B.length; i++) {
                    B[i] /= 2;
                }
                cnt++;
            } else {
                for (int i = 0; i < B.length; i++) {
                    if (B[i] % 2 == 1) {
                        B[i]--;
                        cnt++;
                    }
                }
            }
        }
    }

    private static void input() throws IOException {
        int N = read();
        B = new int[N];

        for (int i = 0; i < N; i++) {
            B[i] = read();
        }
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

        if (c == 13) {
            System.in.read();
        }

        return negative ? -n : n;
    }
}