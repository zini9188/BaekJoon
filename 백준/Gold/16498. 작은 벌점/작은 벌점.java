import java.io.*;
import java.util.Arrays;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int A = read();
        int B = read();
        int C = read();

        int[] a = new int[A];
        int[] b = new int[B];
        int[] c = new int[C];
        for (int i = 0; i < A; i++) {
            a[i] = read();
        }
        Arrays.sort(a);

        for (int i = 0; i < B; i++) {
            b[i] = read();
        }
        Arrays.sort(b);

        for (int i = 0; i < C; i++) {
            c[i] = read();
        }
        Arrays.sort(c);

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < A; i++) {
            int sa = a[i];
            int sb = binarySearch(sa, b);
            int sc1 = binarySearch(sb, c);
            int sc2 = binarySearch(sa, c);

            int max = Math.max(sa, Math.max(sb, sc1));
            int min = Math.min(sa, Math.min(sb, sc1));
            ans = Math.min(ans, Math.abs(max - min));

            max = Math.max(sa, Math.max(sb, sc2));
            min = Math.min(sa, Math.min(sb, sc2));
            ans = Math.min(ans, Math.abs(max - min));
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static int binarySearch(int t, int[] a) {
        int left = 0, right = a.length - 1;
        int mid = (left + right) / 2;
        int near = a[mid];
        while (left <= right) {
            mid = (left + right) / 2;
            if (a[mid] == t) {
                return t;
            }

            if (a[mid] < t) {
                left = mid + 1;
            } else if (a[mid] > t) {
                right = mid - 1;
            }

            if (Math.abs(a[mid] - t) < Math.abs(near - t)) {
                near = a[mid];
            }
        }
        return near;
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