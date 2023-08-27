import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;
    static int a, b, c, d, e, f;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        a = Integer.parseInt(tokenizer.nextToken());
        b = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        d = Integer.parseInt(tokenizer.nextToken());
        e = Integer.parseInt(tokenizer.nextToken());
        f = Integer.parseInt(tokenizer.nextToken());

        int x = -999;
        int y;
        for (; x <= 999; x++) {
            for (y = -999; y <= 999; y++) {
                if (a * x + b * y == c && d * x + e * y == f) {
                    System.out.println(x + " " + y);
                    return;
                }
            }
        }
    }
}