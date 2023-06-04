import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        BigInteger bigInteger = BigInteger.valueOf(1);

        for (int i = 0; i < m; i++) {
            bigInteger = bigInteger.multiply(BigInteger.valueOf(n - i));
        }

        for (int i = 0; i < m; i++) {
            bigInteger = bigInteger.divide(BigInteger.valueOf(i + 1));
        }
        bw.write(bigInteger + "");
        bw.flush();
        bw.close();
        br.close();
    }
}