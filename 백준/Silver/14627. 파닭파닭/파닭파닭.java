import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int S = read();
        int C = read();

        int[] arr = new int[S];
        long sum = 0;
        for (int i = 0; i < S; i++) {
            arr[i] = read();
            sum += arr[i];
        }

        int left = 1, right = 1000000000;
        while (left <= right) {
            int mid = (left + right) / 2;
            long cnt = 0;
            for (int i = 0; i < S; i++) {
                cnt += arr[i] / mid;
            }

            if (cnt < C) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        long ans = sum - ((long) right * C);
        sb.append(ans);
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

        if (c == 13) {
            System.in.read();
        }

        return negative ? -n : n;
    }
}