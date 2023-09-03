import java.io.*;
import java.math.BigInteger;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BigInteger A = new BigInteger(br.readLine());
        BigInteger B = new BigInteger(br.readLine());
        sb.append(A.add(B)).append("\n");
        sb.append(A.subtract(B)).append("\n");
        sb.append(A.multiply(B)).append("\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}