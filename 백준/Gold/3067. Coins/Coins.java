import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        StringBuilder builder = new StringBuilder();
        for (int testCase = 0; testCase < T; testCase++) {
            int N = Integer.parseInt(br.readLine());

            int[] coins = new int[N];
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                coins[i] = Integer.parseInt(tokenizer.nextToken());
            }

            int money = Integer.parseInt(br.readLine());
            int[] dp = new int[money + 1];

            dp[0] = 1;
            for (int coin = N - 1; coin >= 0; coin--) {
                for (int i = coins[coin]; i <= money; i++) {
                    dp[i] = dp[i - coins[coin]] + dp[i];
                }
            }
            builder.append(dp[money]).append("\n");
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}