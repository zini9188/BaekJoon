import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();
        int K = read();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = read();
        }

        long left = 1, right = Integer.MAX_VALUE;
        while (left <= right) {
            long mid = (left + right) >> 1;

            int cnt = 0;
            for (int i = 0; i < N; i++) {
                cnt += arr[i] / mid;
            }

            if (cnt < K) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        sb.append(right);
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