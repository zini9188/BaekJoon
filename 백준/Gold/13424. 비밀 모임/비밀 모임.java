import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static final int MAX = 987654321;

    public static void main(String[] args) throws IOException {
        int T = read();

        for (int i = 0; i < T; i++) {
            input();
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void input() throws IOException {
        int n = read();
        int m = read();

        int[][] dist = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dist[i], MAX);
            dist[i][i] = 0;
        }
        for (int i = 0; i < m; i++) {
            int a = read();
            int b = read();
            int c = read();
            dist[a][b] = c;
            dist[b][a] = c;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int K = read();
        List<Integer> student = new ArrayList<>();
        for (int i = 1; i <= K; i++) {
            student.add(read());
        }

        int ans = MAX;
        int idx = n;
        for (int i = n; i > 0; i--) {
            int res = 0;

            for (Integer stu : student) {
                res += dist[stu][i];
            }

            if (ans >= res) {
                ans = res;
                idx = i;
            }
        }

        sb.append(idx).append("\n");
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}