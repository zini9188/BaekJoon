import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N, A, B;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            N = Integer.parseInt(br.readLine());

            parent = new int[N + 1];
            for (int j = 0; j < N - 1; j++) {
                tokenizer = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(tokenizer.nextToken());
                int B = Integer.parseInt(tokenizer.nextToken());

                parent[B] = A;
            }

            tokenizer = new StringTokenizer(br.readLine());
            A = Integer.parseInt(tokenizer.nextToken());
            B = Integer.parseInt(tokenizer.nextToken());

            int x = A;
            boolean[] isParent = new boolean[N + 1];
            while (parent[x] != 0) {
                isParent[x] = true;
                x = parent[x];
            }

            x = B;
            while (parent[x] != 0 && !isParent[x]) {
                x = parent[x];
            }

            sb.append(x).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}