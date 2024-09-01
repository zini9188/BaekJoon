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

        int x = read();
        int ans = 0;
        for (int left = 0, right = N - 1; left < right; ) {
            int sum = arr[left] + arr[right];

            if (sum >= x) {
                right--;
                if (sum == x) {
                    ans++;
                }
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
        return negative ? -n : n;
    }
}