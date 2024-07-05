import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] arr;
    private static int len, ans, k;

    public static void main(String[] args) throws IOException {
        k = Integer.parseInt(br.readLine());
        len = (int) Math.pow(2, k + 1) - 1;
        arr = new int[len + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        searchTree(1, 0);
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static int searchTree(int node, int depth) {
        if (depth == k) {
            ans += arr[node];
            return arr[node];
        }

        int left = searchTree(node * 2, depth + 1);
        int right = searchTree(node * 2 + 1, depth + 1);

        ans += Math.abs(left - right) + arr[node];
        return Math.max(left, right) + arr[node];
    }

}