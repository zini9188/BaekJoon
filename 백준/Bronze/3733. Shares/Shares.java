import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String line;
        while ((line = br.readLine()) != null) {
            tokenizer = new StringTokenizer(line);
            int N = Integer.parseInt(tokenizer.nextToken());
            int S = Integer.parseInt(tokenizer.nextToken());
            sb.append(S / (N + 1)).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}