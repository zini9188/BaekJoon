import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = read();
        }

        int cost = 0;
        for (int i = 0; i < N; i++) {
            while (arr[i] > 0) {
                if (i < N - 2 && arr[i] > 0 && arr[i + 1] > 0 && arr[i + 2] > 0) {
                    if (arr[i + 1] > arr[i + 2]) {
                        cost += 5;
                    } else {
                        cost += 7;
                        arr[i + 2]--;
                    }
                    arr[i]--;
                    arr[i + 1]--;
                } else if (i < N - 1 && arr[i] > 0 && arr[i + 1] > 0) {
                    arr[i]--;
                    arr[i + 1]--;
                    cost += 5;
                } else {
                    arr[i]--;
                    cost += 3;
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
}