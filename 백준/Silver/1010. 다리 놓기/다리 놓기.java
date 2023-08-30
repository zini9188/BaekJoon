import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());

        for (int test_case = 1; test_case <= T; test_case++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a < b) {
                int tmp = a;
                a = b;
                b = tmp;
            }

            int[][] dp = new int[a + 1][b + 1];

            for (int i = 0; i <= a; i++) {
                for (int j = 0, end = Math.min(i, b); j <= end; j++) {
                    if (j == 0 || i == j) dp[i][j] = 1;
                    else dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }
            }

            System.out.println(dp[a][b]);
        }
    }
}