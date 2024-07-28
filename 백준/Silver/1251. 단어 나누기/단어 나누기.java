import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        String input = br.readLine();
        int len = input.length();

        Queue<String> pq = new PriorityQueue<>();
        for (int i = 1; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                sb = new StringBuilder();
                StringBuilder a = new StringBuilder(input.substring(0, i));
                StringBuilder b = new StringBuilder(input.substring(i, j));
                StringBuilder c = new StringBuilder(input.substring(j, len));
                sb.append(a.reverse()).append(b.reverse()).append(c.reverse());
                String d = String.valueOf(sb);

                pq.add(d);
            }
        }

        sb = new StringBuilder();
        sb.append(pq.poll());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}