import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        String N = tokenizer.nextToken();
        int B = Integer.parseInt(tokenizer.nextToken());
        int ans = 0;
        for (int i = 0; i < N.length(); i++) {
            int cur = Character.isAlphabetic(N.charAt(i)) ? N.charAt(i) - 'A' + 10 : N.charAt(i) - '0';
            int digit = (int) Math.pow(B, N.length() - i - 1);
            ans += cur * digit;
        }
        sb.append(ans);
        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
