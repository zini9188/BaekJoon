import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int K = Integer.parseInt(br.readLine());
        int[] dp = new int[46];
        dp[1] = 1;
        for (int i = 2; i <= K; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        sb.append(dp[K - 1]).append(" ").append(dp[K]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}