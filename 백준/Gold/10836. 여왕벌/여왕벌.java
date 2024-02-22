import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[] temp = new int[2 * M - 1];
        int[] arr = new int[2 * M - 1];
        Arrays.fill(arr, 1);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int zero = Integer.parseInt(st.nextToken());
            int one = Integer.parseInt(st.nextToken());
            int two = Integer.parseInt(st.nextToken());

            int idx = zero;
            int sum = 1;

            if (one > 0) {
                temp[idx]++;
                idx += one;
                sum--;
            }

            if (two > 0) {
                temp[idx] += 1 + sum;
            }
        }

        for (int i = 0; i < 2 * M - 1; i++) {
            for (int j = 0; j <= i; j++) {
                arr[i] += temp[j];
            }
        }

        for (int i = M - 1; i >= 0; i--) {
            sb.append(arr[i]).append(" ");
            for (int j = M; j < 2 * M - 1; j++) {
                sb.append(arr[j]).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}