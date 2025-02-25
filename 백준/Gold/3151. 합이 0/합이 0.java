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
        Arrays.sort(arr);

        long ans = 0;
        for (int i = 0; i < N - 2; i++) {
            for (int j = i + 1; j < N - 1; j++) {
                long cnt = arr[i] + arr[j];

                int idx1 = lowerBound(arr, -cnt, j + 1);
                int idx2 = upperBound(arr, -cnt, j + 1);
                ans += idx2 - idx1;
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int lowerBound(int[] array, long key, int start) {
        int left = start, right = array.length;
        while (left < right) {
            int mid = (right + left) >> 1;
            if (array[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private static int upperBound(int[] array, long key, int start) {
        int left = start, right = array.length;
        while (left < right) {
            int mid = (right + left) >> 1;
            if (array[mid] <= key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
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