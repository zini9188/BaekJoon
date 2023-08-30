import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[3][N][N]; // 0: 가로, 1: 세로, 2: 대각선
        for (int i = 1; i < N; i++) {
            if (A[0][i] == 1) break;
            dp[0][0][i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j < N; j++) {
                // 대각선
                if (A[i][j] == 0 && A[i - 1][j] == 0 && A[i][j - 1] == 0) {
                    dp[2][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
                }

                if (A[i][j] == 0) {
                    // 가로
                    dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1];
                    // 세로
                    dp[1][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j];
                }
            }
        }
        System.out.println(dp[0][N - 1][N - 1] + dp[1][N - 1][N - 1] + dp[2][N - 1][N - 1]);
    }
}