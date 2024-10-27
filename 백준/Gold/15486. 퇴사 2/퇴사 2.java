import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[][] arr = new int[N + 1][2];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            int next = arr[i][0] + i;
            if (next - 1 <= N) {
                dp[next - 1] = Math.max(arr[i][1] + dp[i - 1], dp[next - 1]);
            }

            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        sb.append(dp[N]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}