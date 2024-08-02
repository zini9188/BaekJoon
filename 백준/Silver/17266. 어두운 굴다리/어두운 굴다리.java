import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] arr;
    private static int M, N;

    public static void main(String[] args) throws IOException {
        N = read();
        M = read();
        arr = new int[M + 1];
        for (int i = 0; i < M; i++) {
            arr[i] = read();
        }

        int left = 1, right = 100000;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (check(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        
        sb.append(right);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean check(int mid) {
        int light = 0;
        for (int i = 0; i < M; i++) {
            if (arr[i] - mid > light) {
                return false;
            }
            light = arr[i] + mid;
        }
        return light >= N;
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}