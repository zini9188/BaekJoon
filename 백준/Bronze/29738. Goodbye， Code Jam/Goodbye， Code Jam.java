import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int T = read();
        for (int i = 0; i < T; i++) {
            sb.append(String.format("Case #%d: ", i + 1));

            int n = read();

            if (n > 4500) {
                sb.append("Round 1");
            } else if (n > 1000) {
                sb.append("Round 2");
            } else if (n > 25) {
                sb.append("Round 3");
            } else {
                sb.append("World Finals");
            }

            sb.append("\n");
        }

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