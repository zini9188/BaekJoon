import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();
        long B = readLong();
        long C = readLong();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = read();
        }

        long second = B + C;
        long third = B + (2 * C);

        long cost = 0;
        long cnt = 0;
        for (int i = 0; i < N; i++) {
            while (arr[i] > 0) {
                if (B < C) {
                    cost += B * arr[i];
                    arr[i] = 0;
                } else {
                    if (i < N - 2 && arr[i] > 0 && arr[i + 1] > 0 && arr[i + 2] > 0) {
                        cnt = Math.min(Math.min(arr[i], arr[i + 1]), arr[i + 2]);
                        if (arr[i + 1] > arr[i + 2]) {
                            cnt = Math.min(arr[i], arr[i + 1] - arr[i + 2]);
                            cost += second * cnt;
                        } else {
                            cost += third * cnt;
                            arr[i + 2] -= cnt;
                        }
                        arr[i] -= cnt;
                        arr[i + 1] -= cnt;
                    } else if (i < N - 1 && arr[i] > 0 && arr[i + 1] > 0) {
                        cnt = Math.min(arr[i], arr[i + 1]);
                        arr[i] -= cnt;
                        arr[i + 1] -= cnt;
                        cost += second * cnt;
                    } else {
                        cost += B * arr[i];
                        arr[i] = 0;
                    }
                }
            }
        }

        sb.append(cost);
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

    private static long readLong() throws IOException {
        long c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}