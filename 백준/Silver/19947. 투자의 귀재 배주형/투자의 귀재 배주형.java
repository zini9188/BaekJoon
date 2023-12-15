import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(tokenizer.nextToken());
        int Y = Integer.parseInt(tokenizer.nextToken());

        int[] dp = new int[Y + 1];
        dp[0] = H;

        for (int i = 1; i < Y + 1; i++) {
            if (i >= 5) {
                dp[i] = (int) Math.max(dp[i - 5] * 1.35, dp[i]);
            }

            if (i >= 3) {
                dp[i] = (int) Math.max(dp[i - 3] * 1.2, dp[i]);
            }

            dp[i] = (int) Math.max((dp[i - 1] * 1.05), dp[i]);
        }

        sb.append(dp[Y]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}