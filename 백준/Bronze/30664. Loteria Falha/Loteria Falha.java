import java.io.*;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String line;
        BigInteger n42 = new BigInteger("42");
        while (!(line = br.readLine()).equals("0")) {
            BigInteger b = new BigInteger(line);
            if (b.mod(n42).equals(BigInteger.ZERO)) {
                sb.append("PREMIADO\n");
            } else {
                sb.append("TENTE NOVAMENTE\n");
            }
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}