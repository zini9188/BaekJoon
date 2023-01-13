import java.io.*;
import java.util.StringTokenizer;


public class Main {
    static int N, r, c;
    static int count = 0;

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        r = Integer.parseInt(tokenizer.nextToken());
        c = Integer.parseInt(tokenizer.nextToken());
        divide(0, 0, (int) Math.pow(2, N));
        bw.write(count + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static void divide(int x, int y, int n) {
        if (n == 1) return;
        if (r < x + n / 2 && c < y + n / 2) {
            divide(x, y, n / 2);
        }
        else if (r < x + n / 2 && c < y + n) {
            count += (int) Math.pow(n / 2, 2);
            divide(x, y + n / 2, n / 2);
        }
        else if (r < x + n && c < y + n / 2) {
            count += (int) Math.pow(n / 2, 2) * 2;
            divide(x + n / 2, y, n / 2);
        }
        else if (x + n / 2 <= r && y + n / 2 <= c) {
            count += (int) Math.pow(n / 2, 2) * 3;
            divide(x + n / 2, y + n / 2, n / 2);
        }
    }
}