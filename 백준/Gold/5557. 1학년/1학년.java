import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N, ans;
    private static int[] arr;
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        arr = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new long[21][101];
        for (int i = 0; i < 21; i++) {
            Arrays.fill(dp[i], -1);
        }

        sb.append(dfs(1, arr[1]));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static long dfs(int depth, int sum) {
        if (sum < 0 || sum > 20) {
            return 0;
        }

        if (depth == N - 1) {
            return sum == arr[N] ? 1 : 0;
        }

        if (dp[sum][depth] != -1) {
            return dp[sum][depth];
        }

        return dp[sum][depth] = dfs(depth + 1, sum + arr[depth + 1]) + dfs(depth + 1, sum - arr[depth  +1]);
    }
}