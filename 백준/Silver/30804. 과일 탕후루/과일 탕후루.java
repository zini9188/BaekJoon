import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        int[] fruits = new int[N];
        for (int i = 0; i < N; i++) {
            fruits[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0, left = 0, right = 0;
        int[] use = new int[10];
        int ans = 0;
        while (left <= right && right < N) {
            if (cnt <= 2) {
                if (use[fruits[right++]]++ == 0) {
                    cnt++;
                }
                if (cnt <= 2) {
                    ans = Math.max(ans, right - left);
                }
            } else {
                if (--use[fruits[left++]] == 0) {
                    cnt--;
                }
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}