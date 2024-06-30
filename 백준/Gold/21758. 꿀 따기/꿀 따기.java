import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N + 1];
        int[] sum = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i] = sum[i - 1] + arr[i];
        }

        int max = 0;
        for (int i = 2; i < N; i++) {
            int res = sum[N] * 2 - sum[i] - sum[1] - arr[i];
            max = Math.max(res, max);
        }

        for (int i = 2; i < N; i++) {
            int res = sum[N - 1] - sum[1] + arr[i];
            max = Math.max(res, max);
        }

        for (int i = N - 1; i > 1; i--) {
            int res = sum[i] - arr[i] + sum[N - 1] - arr[i];
            max = Math.max(res, max);
        }

        sb.append(max);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}