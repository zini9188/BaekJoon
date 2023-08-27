import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        BigInteger a = new BigInteger(tokenizer.nextToken());
        BigInteger b = new BigInteger(tokenizer.nextToken());
        sb.append(a.add(b));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}