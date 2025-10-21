import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] parent;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        long[] arr = new long[N + 1];
        List<Integer> index = new ArrayList<>();
        long ans = 0;
        for (int i = 1; i <= N; i++) {
            arr[i] = readLong();
            if (arr[i] > 0) { // 쪽방을 갖고 있음
                index.add(i);
                ans += arr[i];
            }
        }

        if (index.isEmpty()) {
            ans = N / 2;
        } else {
            for (int i = 0; i < index.size(); i++) {
                if (i == index.size() - 1) {
                    // 원형처리
                    ans += (N - index.get(i) + index.get(0)) / 2;
                } else {
                    ans += (index.get(i + 1) - index.get(i)) / 2;
                }
            }
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

    private static long readLong() throws IOException {
        long c, n = System.in.read() & 15;
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