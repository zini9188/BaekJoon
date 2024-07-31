import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = read();
        }

        int left = 0, right = N - 1;
        long ans = Long.MAX_VALUE;
        while (left < right) {
            long b = arr[left] + arr[right];

            if (Math.abs(ans) > Math.abs(b)) {
                ans = b;
            }

            if (b >= 0) {
                right--;
            } else {
                left++;
            }
        }

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