import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int INF = 1000 * 1000 + 1;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[3][N + 1];
        StringTokenizer tokenizer;
        for (int i = 1; i < N + 1; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int red = Integer.parseInt(tokenizer.nextToken());
            int green = Integer.parseInt(tokenizer.nextToken());
            int blue = Integer.parseInt(tokenizer.nextToken());
            cost[0][i] = red;
            cost[1][i] = green;
            cost[2][i] = blue;
        }

        int[][] dp = new int[3][N + 1];
        int answer = INF;
        for (int first = 0; first < 3; first++) {
            for (int color = 0; color < 3; color++) {
                if (first == color) dp[color][1] = cost[first][1];
                else dp[color][1] = INF;
            }

            for (int n = 2; n <= N; n++) {
                dp[0][n] = Math.min(dp[1][n - 1], dp[2][n - 1]) + cost[0][n];
                dp[1][n] = Math.min(dp[0][n - 1], dp[2][n - 1]) + cost[1][n];
                dp[2][n] = Math.min(dp[0][n - 1], dp[1][n - 1]) + cost[2][n];
            }

            for (int color = 0; color < 3; color++) {
                if (first == color) continue;
                answer = Math.min(answer, dp[color][N]);
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}