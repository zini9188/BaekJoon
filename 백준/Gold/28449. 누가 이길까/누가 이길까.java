import java.io.*;
import java.util.Arrays;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();
        int M = read();

        int[] HI = new int[N];
        for (int i = 0; i < N; i++) {
            HI[i] = read();
        }

        int[] ARC = new int[M];
        for (int i = 0; i < M; i++) {
            ARC[i] = read();
        }

        Arrays.sort(ARC);
        Arrays.sort(HI);

        long hiWin = 0, draw = 0, arcWin = 0;
        for (int ability : HI) {
            int left = 0, right = M - 1;
            int start = M;
            while (left <= right) {
                int mid = (left + right) >> 1;

                if (ability > ARC[mid]) {
                    left = mid + 1;
                } else {
                    start = mid;
                    right = mid - 1;
                }
            }

            int end = -1;
            left = 0;
            right = M - 1;

            while (left <= right) {
                int mid = (left + right) >> 1;

                if (ability < ARC[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                    end = mid;
                }
            }

            hiWin += start;
            draw += (end - start + 1);
            arcWin += M - right - 1;
        }

        sb.append(hiWin).append(" ").append(arcWin).append(" ").append(draw);
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