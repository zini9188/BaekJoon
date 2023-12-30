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
        int[][] arr = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            String input = br.readLine();
            for (int j = 0; j < N; j++) {
                arr[i][j + 1] = input.charAt(j) == 'N' ? 0 : 1;
            }
        }

        int[][] temp = new int[N + 1][N + 1];
        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    if (i == j) {
                        continue;
                    }

                    if (arr[i][k] == 1 && arr[k][j] == 1) {
                        temp[i][j] = 1;
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 1; i < N + 1; i++) {
            int sum = 0;
            for (int j = 1; j < N + 1; j++) {
                if (arr[i][j] == 1 || temp[i][j] == 1) {
                    sum++;
                }
            }

            ans = Math.max(ans, sum);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}