import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            int[] arr = new int[n];
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(arr);

            int left = 0, right = n - 1;
            int near = Integer.MAX_VALUE;
            int ans = 0;
            while (left < right) {
                int sum = arr[left] + arr[right];
                int sub = Math.abs(sum - k);

                if (near >= sub) {
                    if (near > sub) {
                        ans = 0;
                        near = sub;
                    }
                    ans++;
                }

                if (sum >= k) {
                    right--;
                } else {
                    left++;
                }
            }
            sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}