import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();

        boolean[][] visited = new boolean[1001][1001];
        Queue<Info> q = new ArrayDeque<>();
        q.add(new Info(1, 0, 0));
        visited[0][1] = true;
        while (!q.isEmpty()) {
            Info info = q.poll();
            if (info.amount == N) {
                System.out.println(info.time);
                break;
            }

            q.add(new Info(info.amount, info.time + 1, info.amount));

            if (info.copy != 0 && info.amount + info.copy <= N && !visited[info.copy][info.amount + info.copy]) {
                q.add(new Info(info.copy + info.amount, info.time + 1, info.copy));
                visited[info.copy][info.amount + info.copy] = true;
            }

            if (info.amount >= 1 && !visited[info.copy][info.amount - 1]) {
                q.add(new Info(info.amount - 1, info.time + 1, info.copy));
                visited[info.copy][info.amount - 1] = true;
            }
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

    private static class Info {

        int amount, time, copy;

        public Info(int amount, int time, int copy) {
            this.amount = amount;
            this.time = time;
            this.copy = copy;
        }
    }
}