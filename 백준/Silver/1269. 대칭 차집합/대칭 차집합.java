import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(tokenizer.nextToken());
        int B = Integer.parseInt(tokenizer.nextToken());

        boolean[] visited = new boolean[100000001];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            visited[Integer.parseInt(tokenizer.nextToken())] = true;
        }

        int same = 0;
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            if (visited[Integer.parseInt(tokenizer.nextToken())]) {
                same++;
            }
        }

        sb.append(A + B - 2 * same);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}