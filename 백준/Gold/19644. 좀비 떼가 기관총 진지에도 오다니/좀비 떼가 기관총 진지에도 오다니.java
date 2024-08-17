import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static int L, C, damage, range;
    private static int[] road;

    public static void main(String[] args) throws IOException {
        input();
        sb.append(solution());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void input() throws IOException {
        L = read();
        range = read();
        damage = read();
        C = read();
        road = new int[L + 1];
        for (int i = 1; i <= L; i++) {
            road[i] = read();
        }
    }

    private static String solution() {
        long[] prefix = new long[L + 1];
        for (int i = 1; i <= L; i++) {
            long d = prefix[i - 1] - prefix[Math.max(0, i - range)];

            if (road[i] <= d + damage) {
                prefix[i] = prefix[i - 1] + damage;
            } else {
                if (C <= 0) {
                    return "NO";
                }

                C--;
                prefix[i] = prefix[i - 1];
            }
        }

        return "YES";
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}