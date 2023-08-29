import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[][] rgb = new int[1001][3];

        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            rgb[i][0] = Integer.parseInt(tokenizer.nextToken());
            rgb[i][1] = Integer.parseInt(tokenizer.nextToken());
            rgb[i][2] = Integer.parseInt(tokenizer.nextToken());
        }
        sb.append(getRGB(N, rgb));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int getRGB(int N, int[][] rgb) {
        for (int i = 1; i <= N; i++) {
            rgb[i][0] += Math.min(rgb[i - 1][1], rgb[i - 1][2]);
            rgb[i][1] += Math.min(rgb[i - 1][0], rgb[i - 1][2]);
            rgb[i][2] += Math.min(rgb[i - 1][1], rgb[i - 1][0]);
        }
        return Math.min(rgb[N][0], Math.min(rgb[N][1], rgb[N][2]));
    }
}