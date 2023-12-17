import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        tokenizer = new StringTokenizer(br.readLine());

        int M = 0;
        int Y = 0;
        for (int i = 0; i < T; i++) {
            int s = Integer.parseInt(tokenizer.nextToken());
            M += ((s / 60) + 1) * 15;
            Y += ((s / 30) + 1) * 10;
        }

        if (M == Y) {
            sb.append("Y M ").append(Y);
        } else if (Y < M) {
            sb.append("Y ").append(Y);
        } else {
            sb.append("M ").append(M);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}