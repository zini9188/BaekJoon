import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        long[] dp = new long[N + 1];
        for (int i = 1; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i >= 6) {
                for (int j = 2; j < 6; j++) {
                    dp[i] = Math.max(dp[i - j - 1] * j, dp[i]);
                }
            }
        }

        sb.append(dp[N]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}