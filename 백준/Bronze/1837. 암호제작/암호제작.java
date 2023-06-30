import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        boolean[] isPrime = new boolean[1000001];
        for (int i = 2; i <= 1000000; i++) {
            if (isPrime[i]) {
                continue;
            }

            for (int j = i * 2; j <= 1000000; j += i) {
                isPrime[j] = true;
            }
        }

        BigInteger P = new BigInteger(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        for (int p = 2; p < K; p++) {
            if(!isPrime[p]){
                BigInteger trans = new BigInteger(String.valueOf(p));
                if (P.mod(trans).equals(BigInteger.ZERO)) {
                    System.out.println("BAD " + p);
                    return;
                }
            }
        }
        System.out.println("GOOD");
    }
}