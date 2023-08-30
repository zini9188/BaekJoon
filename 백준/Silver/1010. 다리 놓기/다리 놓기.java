import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());

        int[][] dp = new int[31][31];
        for (int i = 1; i <= 30; i++) {
            dp[1][i] = i;
        }

        for (int i = 2; i <= 30; i++) {
            for (int j = i; j <= 30; j++) {
                if (i == j) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i][j - 1] + dp[i - 1][j - 1];
                }
            }
        }

        for (int i = 0; i < t; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            sb.append(dp[Integer.parseInt(tokenizer.nextToken())][Integer.parseInt(tokenizer.nextToken())]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}