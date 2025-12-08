import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        long X = Long.parseLong(st.nextToken());

        long[] dp = new long[N + 1];
        dp[0] = 1;
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] * 2 + 3;
        }

        sb.append(calcPattiesAmount(X, dp, N));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static long calcPattiesAmount(long X, long[] dp, int index) {
        if (X <= 0) {
            return 0;
        }
        if (index == 0) {
            if (X == 1) {
                return 1;
            }
            return 0;
        }

        long centerPattyIndex = dp[index] / 2 + 1;

        long mul = (long) (Math.pow(2, index) - 1);

        if (X == dp[index]) {
            return mul * 2 + 1;
        } else if (centerPattyIndex == X) {
            return mul + 1;
        } else if (centerPattyIndex > X) {
            return calcPattiesAmount(X - 1, dp, index - 1);
        }
        return mul + 1 + calcPattiesAmount(X - centerPattyIndex, dp, index - 1);
    }
}