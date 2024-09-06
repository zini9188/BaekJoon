import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int input;
        while ((input = read()) != 0) {
            sb.append(input);
            if (input % 4 != 0 || input < 1896) {
                sb.append(" No summer games");
            } else if (input > 2020) {
                sb.append(" No city yet chosen");
            } else if ((1914 <= input && input <= 1918) || (1939 <= input && input <= 1945)) {
                sb.append(" Games cancelled");
            } else {
                sb.append(" Summer Olympics");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return negative ? -n : n;
    }
}