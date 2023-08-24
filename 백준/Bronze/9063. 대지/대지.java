import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int xl = Integer.MAX_VALUE, yl = Integer.MAX_VALUE, xr = Integer.MIN_VALUE, yr = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            xl = Math.min(xl, x);
            yl = Math.min(yl, y);
            xr = Math.max(xr, x);
            yr = Math.max(yr, y);
        }
        sb.append((yr - yl) * (xr - xl));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
