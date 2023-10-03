import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            long N = Long.parseLong(br.readLine());

            while (!isPrime(N)) {
                N++;
            }
            sb.append(N).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean isPrime(long N) {
        if (N <= 1) {
            return false;
        }

        for (long i = 2; i * i <= N; i++) {
            if (N % i == 0) {
                return false;
            }
        }

        return true;
    }
}