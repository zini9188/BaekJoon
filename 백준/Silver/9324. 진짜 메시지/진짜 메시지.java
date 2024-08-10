import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int[] alpha;
        int T = read();
        for (int i = 0; i < T; i++) {
            alpha = new int[27];
            String message = br.readLine();
            sb.append(check(alpha, message)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String check(int[] alpha, String message) {
        for (int j = 0; j < message.length(); j++) {
            int c = message.charAt(j) - 'A';

            if (++alpha[c] % 3 == 0) {
                if (j + 1 < message.length() && message.charAt(j) == message.charAt(j + 1)) {
                    j++;
                    alpha[c] = 0;
                } else {
                    return "FAKE";
                }
            }
        }
        return "OK";
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}