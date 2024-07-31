import java.io.*;
import java.util.Arrays;

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
        Arrays.sort(arr);

        int sum = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (i >= 2) {
                sum += arr[i] + arr[i - 1];
                i -= 2;
            } else {
                sum += arr[i];
            }
        }

        sb.append(sum);
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