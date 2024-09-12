import java.io.*;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = read();

        Queue<Cow> cows = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            int a = read();
            int b = read();
            cows.add(new Cow(a, b));
        }

        int cur = 0;
        while (!cows.isEmpty()) {
            Cow cow = cows.poll();

            if (cow.a > cur) {
                cur = cow.a + cow.b;
            } else {
                cur += cow.b;
            }
        }

        sb.append(cur);
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

    private static class Cow implements Comparable<Cow> {

        int a, b;

        public Cow(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Cow o) {
            return a - o.a;
        }
    }
}