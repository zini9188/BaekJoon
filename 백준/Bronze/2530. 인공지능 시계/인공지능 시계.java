import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());
        int s = Integer.parseInt(tokenizer.nextToken());
        int t = Integer.parseInt(br.readLine());
        int time = h * 60 * 60 + m * 60 + s + t;

        sb.append(time / 60 / 60 % 24).append(" ");
        sb.append(time / 60 % 60).append(" ");
        sb.append(time % 60 % 60).append(" ");

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}