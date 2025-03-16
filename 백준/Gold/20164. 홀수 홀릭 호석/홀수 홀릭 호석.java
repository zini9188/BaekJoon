import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        String num = br.readLine();
        dfs(num, 0);
        sb.append(min).append(" ").append(max);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(String num, int count) {
        int len = num.length();

        int cnt = 0;
        for (int i = 0; i < len; i++) {
            if ((num.charAt(i) - '0') % 2 == 1) {
                cnt++;
            }
        }

        count += cnt;
        if (len == 1) {
            min = Math.min(min, count);
            max = Math.max(max, count);
            return;
        }

        if (len == 2) {
            int a = num.charAt(0) - '0';
            int b = num.charAt(1) - '0';
            dfs(String.valueOf(a + b), count);
            return;
        }

        for (int i = 1; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int fir = Integer.parseInt(num.substring(0, i));
                int sec = Integer.parseInt(num.substring(i, j));
                int thd = Integer.parseInt(num.substring(j));
                String newNum = String.valueOf((fir + sec + thd));
                dfs(newNum, count);
            }
        }
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