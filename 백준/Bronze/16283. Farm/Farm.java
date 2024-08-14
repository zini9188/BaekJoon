import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int a = read();
        int b = read();
        int n = read();
        int w = read();

        int[] res = new int[2];
        int cnt = 0;
        for (int i = 1; i < n; i++) {
            int j = n - i;
            if (a * i + j * b == w) {
                res[0] = i;
                res[1] = j;
                cnt++;
            }
        }

        if (cnt == 1) {
            sb.append(res[0]).append(" ").append(res[1]);
        } else {
            sb.append("-1");
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