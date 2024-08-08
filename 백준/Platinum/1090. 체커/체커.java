import java.io.*;
import java.util.Arrays;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();
        Point[] points = new Point[N];
        for (int i = 0; i < N; i++) {
            points[i] = new Point(read(), read());
        }

        int[] dist = new int[N];
        int[] count = new int[N];
        Arrays.fill(count, Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int tx = points[i].x;
                int ty = points[j].y;

                for (int k = 0; k < N; k++) {
                    dist[k] = Math.abs(tx - points[k].x) + Math.abs(ty - points[k].y);
                }
                Arrays.sort(dist);

                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += dist[k];
                    count[k] = Math.min(count[k], sum);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            sb.append(count[i]).append(" ");
        }
        
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

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}