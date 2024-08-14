import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int a = read();
        int b = read();
        int n = read();
        int w = read();

        List<int[]> result = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < n; j++) {
                if (i + j != n) {
                    continue;
                }

                if (a * i + j * b == w) {
                    result.add(new int[]{i, j});
                }
            }
        }

        if (result.size() != 1) {
            sb.append("-1");
        } else {
            int[] res = result.get(0);
            sb.append(res[0]).append(" ").append(res[1]);
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