import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int[] liquid = new int[N];
        for (int i = 0; i < N; i++) {
            liquid[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(liquid);

        int left = 0, right = N - 1;
        int abs = 1000000000 * 2;
        int answerLeft = -1000000000;
        int answerRight = 1000000000;
        while (left < right) {
            int sum = liquid[left] + liquid[right];
            if (abs >= Math.abs(sum)) {
                abs = Math.abs(sum);
                answerLeft = liquid[left];
                answerRight = liquid[right];
            }
            if (sum >= 0) {
                right--;
            } else {
                left++;
            }
        }

        bw.write(answerLeft + " " + answerRight);
        bw.flush();
        bw.close();
        br.close();
    }
}