import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int[] arr = new int[N + 2];
        long[] sum = new long[N + 2];
        for (int i = 1; i <= N; i++) {
            arr[i] = readInt();
        }
        arr[0] = arr[N + 1] > 0 ? 1 : -1;
        arr[N + 1] = arr[1];

        for (int i = 1; i <= N; i++) {
            // 쪽방이 있는 경우 쪽방을 선택하는게 이득
            if (arr[i] > 0) {
                sum[i] = arr[i];
            } else {
                // 앞, 뒷방을 선택한 케이스 외에 모두 선택 가능함.
                if (!(arr[i - 1] == -1 || arr[i + 1] == -1)) {
                    arr[i] = -1;
                    sum[i] = 1;
                }
            }
        }

        long ans = 0;
        for (int i = 1; i <= N; i++) {
            ans += sum[i];
        }

        sb.append(ans).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }


    private static int readInt() throws IOException {
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