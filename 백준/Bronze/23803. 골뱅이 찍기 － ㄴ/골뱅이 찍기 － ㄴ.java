import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < 4; j++) {
                sb.append("@".repeat(N));
                sb.append("\n");
            }
        }
        for (int i = 0; i < N; i++) {
            sb.append("@".repeat(5).repeat(N));
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}