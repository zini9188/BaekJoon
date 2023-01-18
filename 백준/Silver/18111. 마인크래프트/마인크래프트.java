import java.io.*;
import java.util.*;

public class Main {
    static int N, M, B, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
    static int[][] ground;
    static int[] result = new int[]{Integer.MAX_VALUE, 0};
    static StringBuilder stringBuilder;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        B = Integer.parseInt(tokenizer.nextToken());
        ground = new int[N + 1][M + 1];
        stringBuilder = new StringBuilder();

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                ground[i][j] = Integer.parseInt(tokenizer.nextToken());
                max = Math.max(ground[i][j], max);
                min = Math.min(ground[i][j], min);
            }
        }
        solution();
        stringBuilder.append(result[0]).append(" ").append(result[1]);
        bw.write(stringBuilder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        for (int height = min; height <= max; height++) {
            int time = 0;
            int block = B;
            for (int vertical = 0; vertical < N; vertical++) {
                for (int horizontal = 0; horizontal < M; horizontal++) {
                    int currentHeight = ground[vertical][horizontal];
                    int blockCount = Math.abs(currentHeight - height);
                    if (currentHeight < height) {
                        block -= blockCount;
                        time += blockCount;
                    } else if (currentHeight > height) {
                        block += blockCount;
                        time += blockCount * 2;
                    }
                }
            }
            if (block >= 0 && time <= result[0]) {
                result[0] = time;
                result[1] = height;
            }
        }
    }
}