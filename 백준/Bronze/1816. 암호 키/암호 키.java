import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        long N = read();
        for (int i = 0; i < N; i++) {
            long s = read();

            sb.append(solution(s)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String solution(long s) {
        for (long j = 2; j <= 1000000; j++) {
            if (s % j == 0) {
                return "NO";
            }
        }
        return "YES";
    }

    private static long read() throws IOException {
        long c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}