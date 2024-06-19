import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[1000001];

        for (int i = A + 1; i <= K; i++) {
            if (i % 2 == 0 && i / 2 >= A) {
                dp[i] = Math.min(dp[i - 1], dp[i / 2]) + 1;
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }

        sb.append(dp[K]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}