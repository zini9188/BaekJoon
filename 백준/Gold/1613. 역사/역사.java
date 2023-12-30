import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());

        int[][] arr = new int[n + 1][n + 1];
        for (int i = 0; i < k; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());

            arr[a][b] = -1;
            arr[b][a] = 1;
        }

        for (int l = 1; l < n + 1; l++) {
            for (int i = 1; i < n + 1; i++) {
                for (int j = 1; j < n + 1; j++) {
                    if (arr[i][j] == 0 && arr[i][l] == arr[l][j]) {
                        arr[i][j] = arr[i][l];
                    }
                }
            }
        }

        int s = Integer.parseInt(br.readLine());
        for (int i = 0; i < s; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            sb.append(arr[a][b]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}