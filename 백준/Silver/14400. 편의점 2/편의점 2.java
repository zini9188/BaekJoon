import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine().trim());
        int[] x = new int[N], y = new int[N];
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(x);
        Arrays.sort(y);
        int idx = N / 2;
        if (N % 2 == 0) {
            idx--;
        }

        long ans = 0;
        for (int i = 0; i < N; i++) {
            ans += Math.abs(x[i] - x[idx]) + Math.abs(y[i] - y[idx]);
        }
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}