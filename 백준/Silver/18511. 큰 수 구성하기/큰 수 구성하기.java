import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    private static int[] arr;
    private static int N, K, ans;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[K];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        dfs(0);

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int num) {
        if (num > N) {
            return;
        }

        if (ans < num) {
            ans = num;
        }

        for (int i = K - 1; i >= 0; i--) {
            dfs(num * 10 + arr[i]);
        }
    }
}