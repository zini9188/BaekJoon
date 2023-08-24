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
        for (int i = 0; i < N + 1; i++) {
            basket[i] = i;
        }

        for (int m = 0; m < M; m++) {
            tokenizer = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(tokenizer.nextToken());
            int j = Integer.parseInt(tokenizer.nextToken());
            swap(i, j, basket);
        }

        for (int i = 1; i < N + 1; i++) {
            sb.append(basket[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void swap(int x, int y, int[] arr) {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, x, copy, x, y + 1 - x);
        for (int i = 0; i <= y - x; i++) {
            arr[i + x] = copy[y - i];
        }
    }
}
