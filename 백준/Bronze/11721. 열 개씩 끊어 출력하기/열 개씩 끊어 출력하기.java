import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String line = br.readLine();
        for (int i = 0; i < line.length(); i++) {
            if (i != 0 && i % 10 == 0) {
                sb.append("\n");
            }
            sb.append(line.charAt(i));
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}