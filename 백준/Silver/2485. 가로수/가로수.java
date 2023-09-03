import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] pos = new int[N];
        for (int i = 0; i < N; i++) {
            pos[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(pos);

        int gcd = 0;
        for (int i = 0; i < N - 1; i++) {
            int space = pos[i + 1] - pos[i];
            gcd = GCD(gcd, space);
        }

        sb.append((pos[N -1] - pos[0]) / gcd + 1 - N);
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