import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();
        int target = read();

        int[][] map = new int[N][N];

        int n = 1;
        int x = N / 2, y = N / 2;
        int limit = 1;
        while (n <= N * N) {
            for (int i = 0; i < limit; i++) {
                map[x--][y] = n++;
            }

            if (n - 1 == N * N) {
                break;
            }

            for (int i = 0; i < limit; i++) {
                map[x][y++] = n++;
            }
            limit++;

            for (int i = 0; i < limit; i++) {
                map[x++][y] = n++;
            }

            for (int i = 0; i < limit; i++) {
                map[x][y--] = n++;
            }

            limit++;
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == target) {
                    x = i;
                    y = j;
                }

                sb.append(map[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append(x + 1).append(" ").append(y + 1);
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