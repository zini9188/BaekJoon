import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String alpha = br.readLine();
            sb.append(alpha.charAt(0)).append(alpha.charAt(alpha.length() - 1)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
