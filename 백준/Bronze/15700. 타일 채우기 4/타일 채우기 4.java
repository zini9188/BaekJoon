import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        long N = Long.parseLong(tokenizer.nextToken());
        long M = Long.parseLong(tokenizer.nextToken());
        sb.append(N * M / 2);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}