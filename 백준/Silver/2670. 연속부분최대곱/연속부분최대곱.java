import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int N = Integer.parseInt(br.readLine());

        double[] arr = new double[N];
        double[] dp = new double[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Double.parseDouble(br.readLine());
        }

        dp[0] = arr[0];
        double ans = 0.0;
        for (int i = 1; i < N; i++) {
            dp[i] = Math.max(arr[i], dp[i - 1] * arr[i]);
            ans = Math.max(dp[i], ans);
        }

        sb.append(String.format("%.3f", ans));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}