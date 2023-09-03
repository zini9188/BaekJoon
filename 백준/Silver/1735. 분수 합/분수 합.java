import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int A1 = Integer.parseInt(tokenizer.nextToken());
        int B1 = Integer.parseInt(tokenizer.nextToken());

        tokenizer = new StringTokenizer(br.readLine());
        int A2 = Integer.parseInt(tokenizer.nextToken());
        int B2 = Integer.parseInt(tokenizer.nextToken());

        int numerator = A1 * B2 + A2 * B1;
        int denominator = B1 * B2;

        int div = GCD(numerator, denominator);
        numerator /= div;
        denominator /= div;
        sb.append(numerator).append(" ").append(denominator);
        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int GCD(int a, int b) {
        if (a % b == 0) {
            return b;
        }
        return GCD(b, a % b);
    }
}