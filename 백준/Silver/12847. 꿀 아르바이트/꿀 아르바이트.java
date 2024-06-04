import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(st.nextToken());
            arr[i] = a;
        }

        int left = 0, right = 0;
        long sum = 0, ans = 0;
        while (left <= right && right < n) {
            if (right - left + 1 > m) {
                sum -= arr[left++];
            } else {
                sum += arr[right++];
            }

            ans = Math.max(sum, ans);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}