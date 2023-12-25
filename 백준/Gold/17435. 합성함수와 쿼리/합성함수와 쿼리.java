import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int M = Integer.parseInt(br.readLine());

        int log = (int) Math.ceil(Math.log(500000) / Math.log(2)) + 1;
        int[][] arr = new int[log][M + 1];

        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            arr[0][i] = Integer.parseInt(tokenizer.nextToken());
        }

        for (int i = 1; i < log; i++) {
            for (int j = 1; j <= M; j++) {
                arr[i][j] = arr[i - 1][arr[i - 1][j]];
            }
        }

        int Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int x = Integer.parseInt(tokenizer.nextToken());

            for (int j = 0; n > 0; j++) {
                if ((n & 1) == 1) {
                    x = arr[j][x];
                }
                n >>= 1;
            }

            sb.append(x).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}