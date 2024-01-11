import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[][] house;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        house = new int[N + 1][N + 1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][][] dp = new long[3][N + 1][N + 1];
        dp[0][0][1] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (house[i][j] == 1) {
                    continue;
                }

                if (j < N - 1 && house[i][j + 1] == 0) {
                    dp[0][i][j + 1] += dp[0][i][j] + dp[2][i][j];
                }

                if (i < N - 1 && house[i + 1][j] == 0) {
                    dp[1][i + 1][j] += dp[1][i][j] + dp[2][i][j];
                }

                if (i < N - 1 && j < N - 1
                        && house[i][j + 1] == 0
                        && house[i + 1][j] == 0
                        && house[i + 1][j + 1] == 0) {
                    dp[2][i + 1][j + 1] += dp[0][i][j] + dp[1][i][j] + dp[2][i][j];
                }
            }
        }

        long sum = 0;
        for (int i = 0; i < 3; i++) {
            sum += dp[i][N - 1][N - 1];
        }

        sb.append(sum);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}