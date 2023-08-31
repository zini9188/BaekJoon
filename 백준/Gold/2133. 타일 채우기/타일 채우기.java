import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[31];
        dp[2] = 3;
        dp[0] = 1;

        int edgeCase = 0;
        for (int i = 4; i <= n; i += 2) {
            dp[i] = dp[i - 2] * 3;
            edgeCase += dp[i - 4] * 2;
            dp[i] += edgeCase;
        }

        sb.append(dp[n]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}