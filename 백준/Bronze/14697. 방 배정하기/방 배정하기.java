import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int A = read();
        int B = read();
        int C = read();
        int N = read();

        sb.append(solution(A, B, C, N) ? 1 : 0);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean solution(int a, int b, int c, int n) {
        int x = n / a;
        int y = n / b;
        int z = n / c;
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                for (int k = 0; k <= z; k++) {
                    if (a * i + j * b + c * k == n) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}