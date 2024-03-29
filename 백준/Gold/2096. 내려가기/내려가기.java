import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] board;
    static int[][] maxDp, minDp;
    static int max, min = Integer.MAX_VALUE;
    static StringBuilder stringBuilder;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer;
        stringBuilder = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        board = new int[N][3];
        maxDp = new int[N][3];
        minDp = new int[N][3];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        init();
        solution();
        findResult();
        bw.write(stringBuilder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        for (int i = 1; i < N; i++) {
            maxDp[i][0] = board[i][0] + Math.max(maxDp[i - 1][0], maxDp[i - 1][1]);
            maxDp[i][1] = board[i][1] + Math.max(Math.max(maxDp[i - 1][1], maxDp[i - 1][2]), maxDp[i - 1][0]);
            maxDp[i][2] = board[i][2] + Math.max(maxDp[i - 1][2], maxDp[i - 1][1]);

            minDp[i][0] = board[i][0] + Math.min(minDp[i - 1][0], minDp[i - 1][1]);
            minDp[i][1] = board[i][1] + Math.min(Math.min(minDp[i - 1][1], minDp[i - 1][2]), minDp[i - 1][0]);
            minDp[i][2] = board[i][2] + Math.min(minDp[i - 1][2], minDp[i - 1][1]);
        }
    }

    private static void findResult() {
        min = Math.min(Math.min(minDp[N - 1][0], minDp[N - 1][1]), minDp[N - 1][2]);
        max = Math.max(Math.max(maxDp[N - 1][0], maxDp[N - 1][1]), maxDp[N - 1][2]);
        stringBuilder.append(max).append("\n").append(min);
    }

    private static void init() {
        maxDp[0][0] = minDp[0][0] = board[0][0];
        maxDp[0][1] = minDp[0][1] = board[0][1];
        maxDp[0][2] = minDp[0][2] = board[0][2];
    }
}