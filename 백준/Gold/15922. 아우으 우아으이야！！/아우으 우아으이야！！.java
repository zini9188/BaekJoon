import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int left = 0, right = 0, ans = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            if (i == 0) {
                left = x;
                right = y;
            } else {
                if (right >= x) {
                    right = Math.max(right, y);
                } else {
                    ans += right - left;
                    left = x;
                    right = y;
                }
            }
        }
        ans += right - left;

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}