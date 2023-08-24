import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == -1) {
                break;
            }

            sb.append(N).append(" ");
            ArrayList<Integer> divisor = new ArrayList<>();
            int sum = 0;
            for (int i = 1; i < N; i++) {
                if (N % i == 0) {
                    divisor.add(i);
                    sum += i;
                }
            }

            if (sum == N) {
                sb.append("= ");
                for (Integer div : divisor) {
                    sb.append(div).append(" + ");
                }
                sb.delete(sb.length() - 2, sb.length());
                sb.append("\n");
            } else {
                sb.append("is NOT perfect.\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}