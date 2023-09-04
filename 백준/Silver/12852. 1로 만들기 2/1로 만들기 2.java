import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N + 1];
        int[] path = new int[N + 1];
        Arrays.fill(dp, 1 << 12);
        dp[1] = 0;
        for (int i = 2; i <= N; i++) {
            if (dp[i] > dp[i - 1] + 1) {
                dp[i] = dp[i - 1] + 1;
                path[i] = i - 1;
            }
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                path[i] = i / 2;
            }
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                path[i] = i / 3;
            }
        }

        int operate = dp[N];
        sb.append(dp[N]).append("\n");
        while (N > 0) {
            sb.append(N).append(" ");
            N = path[N];
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}