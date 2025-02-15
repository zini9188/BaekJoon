import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = read();
        }
        Arrays.sort(arr);

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                int res = arr[i] - arr[j];

                if (map.containsKey(res)) {
                    int max = map.get(res);
                    map.put(res, Math.max(max, arr[i]));
                } else {
                    map.put(res, arr[i]);
                }
            }
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = arr[i] + arr[j];

                if (map.containsKey(sum)) {
                    max = Math.max(max, map.get(sum));
                }
            }
        }

        sb.append(max);
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