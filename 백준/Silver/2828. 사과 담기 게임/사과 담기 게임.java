import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int J = Integer.parseInt(br.readLine());
        int ans = 0;
        int s = 1, e = M;
        for (int i = 0; i < J; i++) {
            int target = Integer.parseInt(br.readLine());
            if (s > target) {
                ans += s - target;
                e -= s - target;
                s = target;
            } else if (e < target) {
                ans += target - e;
                s += target - e;
                e = target;
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}