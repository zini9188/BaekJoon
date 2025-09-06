import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            pq.add(readInt());
        }

        Queue<Integer> consent = new PriorityQueue<>();
        for (int i = 0; i < M; i++) {
            consent.add(0);
        }

        int time = 0;
        while (!pq.isEmpty()) {
            Integer charging = pq.poll();
            if (!consent.isEmpty()) {
                int endOfCharge = consent.poll() + charging;
                consent.add(endOfCharge);
                time = Math.max(time, endOfCharge);
            }
        }
        
        sb.append(time);
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