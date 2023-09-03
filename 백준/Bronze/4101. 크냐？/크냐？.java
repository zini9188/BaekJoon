import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (true) {
            tokenizer = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            if (A == 0 && B == 0) {
                break;
            }
            sb.append(A > B ? "Yes\n" : "No\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}