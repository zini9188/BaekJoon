import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuilder stringBuilder = new StringBuilder();
        int[] dp = new int[41];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= 40; i++) {
            dp[i] = dp[i - 2] + dp[i - 1];
        }
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) {
                stringBuilder.append("1 0\n");
            } else
                stringBuilder.append(dp[N - 1]).append(" ").append(dp[N]).append("\n");
        }

        bw.write(stringBuilder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
