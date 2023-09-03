import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for (int i = 0; i < 7; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n % 2 == 1) {
                sum += n;
                min = Math.min(min, n);
            }
        }
        if (sum == 0) {
            sb.append("-1");
        } else {
            sb.append(sum).append("\n").append(min);
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}