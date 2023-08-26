import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        while (true) {
            StringBuilder line = new StringBuilder(br.readLine());
            if (line.toString().equals("END")) {
                break;
            }
            sb.append(line.reverse()).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}