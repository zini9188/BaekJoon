import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N;

    public static void main(String[] args) throws IOException {
        int div = 1000000007;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

            Queue<Long> q = new PriorityQueue<>();
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                q.add(Long.parseLong(st.nextToken()));
            }

            long mul = 1;
            while (q.size() > 1) {
                long c = q.poll() * q.poll();
                mul = c % div * mul % div;
                q.add(c);
            }
            sb.append(mul).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}