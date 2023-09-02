import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] m = new int[N];
        int[] c = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            m[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int sum = 0;
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            c[i] = Integer.parseInt(tokenizer.nextToken());
            sum += c[i];
        }

        int[] dp = new int[sum + 1];
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = sum; j >= c[i]; j--) {
                dp[j] = Math.max(dp[j - c[i]] + m[i], dp[j]);
                if (dp[j] >= M) {
                    ans = Math.min(j, ans);
                }
            }
        }

        System.out.println(ans);
    }
}