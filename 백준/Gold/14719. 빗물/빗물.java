import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int H, W;
    static int[] blocksHeight;
    static int[] maxRainHeight;
    static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        H = Integer.parseInt(tokenizer.nextToken());
        W = Integer.parseInt(tokenizer.nextToken());
        blocksHeight = new int[W];
        maxRainHeight = new int[W];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < W; i++) {
            blocksHeight[i] = Integer.parseInt(tokenizer.nextToken());
        }
        solution();
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        maxRainHeight[0] = blocksHeight[0];
        for (int i = 1; i < W - 1; i++) {
            maxRainHeight[i] = Math.max(maxRainHeight[i - 1], blocksHeight[i]);
            int currentHeight = maxRainHeight[i];
            int findMaxByRight = blocksHeight[W - 1];
            for (int j = W - 1; j > i; j--) {
                if (currentHeight <= blocksHeight[j]) {
                    maxRainHeight[i] = currentHeight;
                    break;
                } else {
                    findMaxByRight = Math.max(findMaxByRight, blocksHeight[j]);
                    maxRainHeight[i] = findMaxByRight;
                }
            }
            if (maxRainHeight[i] > blocksHeight[i]) {
                result += maxRainHeight[i] - blocksHeight[i];
            }
        }
    }
}