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
        tokenizer = new StringTokenizer(br.readLine());

        int[] box = new int[N];
        for (int i = 0; i < N; i++) {
            box[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] dp = new int[N];
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (box[i] > box[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            ans = Math.max(dp[i], ans);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}