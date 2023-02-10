import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        int T;
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(tokenizer.nextToken());
            int N = Integer.parseInt(tokenizer.nextToken());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            builder.append(solution(M, N, x, y)).append("\n");
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution(int M, int N, int x, int y) {
        for (int i = x; i <= LCM(M, N); i += M) {
            int y_ = i % N;
            if (y_ == 0) y_ = N;
            if (y_ == y) {
                return i;
            }
        }
        return -1;
    }

    private static int GCD(int a, int b) {
        if (b == 0) return a;
        return GCD(b, a % b);
    }

    private static int LCM(int a, int b) {
        return (a * b) / GCD(a, b);
    }

}