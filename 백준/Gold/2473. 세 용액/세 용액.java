import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        long[] liquid = new long[N];
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            liquid[i] = Long.parseLong(tokenizer.nextToken());
        }
        Arrays.sort(liquid);

        int resL = 0, resR = 0, resM = 0;
        long answer = Long.MAX_VALUE;
        for (int i = 0; i < N - 2; i++) {
            int left = i + 1;
            int right = N - 1;

            while (left < right) {
                long sum = liquid[left] + liquid[right] + liquid[i];

                if (answer > Math.abs(sum)) {
                    answer = Math.abs(sum);
                    resL = i;
                    resM = left;
                    resR = right;
                }

                if (sum >= 0) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        bw.write(liquid[resL] + " " + liquid[resM] + " " + liquid[resR]);
        bw.flush();
        bw.close();
        br.close();
    }
}