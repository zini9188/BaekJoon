import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[100001];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 2;
        dp[4] = 2;
        dp[5] = 1;
        dp[6] = 2;
        dp[7] = 1;
        for (int i = 8; i <= n; i++) {
            dp[i] = Math.min(dp[i - 1], Math.min(dp[i - 2], Math.min(dp[i - 5], dp[i - 7]))) + 1;
        }
        sb.append(dp[n]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}