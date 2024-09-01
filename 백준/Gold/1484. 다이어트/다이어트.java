import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int G = read();

        List<Integer> answer = new ArrayList<>();
        for (int x = 1; x < 100000; x++) {
            long diff = (long) (Math.pow(x, 2) - G);
            if (diff > 0) {
                if (Math.pow((long) Math.sqrt(diff), 2) == diff) {
                    answer.add(x);
                }
            }
        }

        for (Integer ans : answer) {
            sb.append(ans).append("\n");
        }

        if (answer.isEmpty()) {
            sb.append("-1");
        }

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