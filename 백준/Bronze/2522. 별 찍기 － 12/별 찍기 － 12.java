import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i = 1; i <= N; i++) {
            sb.append(" ".repeat(N - i));
            sb.append("*".repeat(i));
            sb.append("\n");
        }
        for (int i = N - 1; i >= 0; i--) {
            sb.append(" ".repeat(N - i));
            sb.append("*".repeat(i));
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}