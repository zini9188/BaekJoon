import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();

        int[] arr = new int[N];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            arr[i] = read();
            set.add(arr[i]);
        }

        int max = 1;
        for (Integer s : set) {
            int cnt = 1;
            int j = arr[0];

            for (int i = 1; i < N; i++) {
                if (arr[i] == s) {
                    continue;
                }

                if (j == arr[i]) {
                    cnt++;
                    if (cnt > max) {
                        max = cnt;
                    }
                } else {
                    cnt = 1;
                }

                j = arr[i];
            }
        }

        sb.append(max);
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
}