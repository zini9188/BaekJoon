import java.io.*;
import java.util.Arrays;

public class Main {
    static final int INF = 987654321;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[5001];
        Arrays.fill(dp, INF);
        dp[3] = 1;
        dp[5] = 1;
        for (int i = 6; i <= N; i++)
            dp[i] = Math.min(dp[i - 5], dp[i - 3]) + 1;

        if (dp[N] >= INF) bw.write("-1");
        else bw.write(String.valueOf(dp[N]));

        bw.flush();
        bw.close();
        br.close();
    }
}