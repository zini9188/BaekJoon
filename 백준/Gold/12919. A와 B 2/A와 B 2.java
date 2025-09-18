import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        String a = br.readLine();
        String b = br.readLine();

        Set<String> set = new HashSet<>();
        Queue<String> q = new ArrayDeque<>();
        q.add(b);
        while (!q.isEmpty()) {
            String str = q.poll();

            if (str.equals(a)) {
                sb.append("1");
                break;
            }

            if (str.charAt(str.length() - 1) == 'A') {
                String t1 = str.substring(0, str.length() - 1);
                if (t1.length() >= a.length() && set.add(t1)) {
                    q.add(t1);
                }
            }

            if (str.charAt(0) == 'B') {
                StringBuilder t2 = new StringBuilder(str.substring(1)).reverse();

                if (t2.length() >= a.length() && set.add(t2.toString())) {
                    q.add(t2.toString());
                }
            }
        }

        sb.append(sb.length() == 0 ? "0" : "\n");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }


    private static int readInt() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        if (c == 13) {
            System.in.read();
        }

        return negative ? -n : n;
    }
}