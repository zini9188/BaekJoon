import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            int[] arr = new int[N];
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(tokenizer.nextToken());
            }
            Arrays.sort(arr);

            int M = Integer.parseInt(br.readLine());
            tokenizer = new StringTokenizer(br.readLine());
            for (int k = 0; k < M; k++) {
                int find = Integer.parseInt(tokenizer.nextToken());
                sb.append(Arrays.binarySearch(arr, find) < 0 ? 0 : 1).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}