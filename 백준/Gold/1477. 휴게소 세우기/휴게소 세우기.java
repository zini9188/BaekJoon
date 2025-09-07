import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = readInt();
        int M = readInt();
        int L = readInt();

        List<Integer> positions = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            positions.add(readInt());
        }
        positions.add(0);
        positions.add(L);
        positions.sort(Integer::compareTo);

        int left = 1, right = 1001;
        int ans = 0;
        while (left <= right) {
            int mid = (left + right) >> 1;

            int built = 0;
            for (int i = 1; i < positions.size(); i++) {
                int dist = positions.get(i) - positions.get(i - 1);
                built += (dist - 1) / mid;
            }

            if (built <= M) {
                right = mid - 1;
                ans = mid;
            } else {
                left = mid + 1;
            }
        }

        sb.append(ans).append("\n");
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