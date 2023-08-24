import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int[] basket = new int[N + 1];
        for (int m = 0; m < M; m++) {
            tokenizer = new StringTokenizer(br.readLine());
            int I = Integer.parseInt(tokenizer.nextToken());
            int J = Integer.parseInt(tokenizer.nextToken());
            int K = Integer.parseInt(tokenizer.nextToken());
            for (int i = I; i <= J; i++) {
                basket[i] = K;
            }
        }

        for (int i = 1; i < N + 1; i++) {
            sb.append(basket[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

}
