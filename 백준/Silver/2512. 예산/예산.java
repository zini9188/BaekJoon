import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] budgets = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        int left = 0, right = -1;
        for (int i = 0; i < N; i++) {
            budgets[i] = Integer.parseInt(tokenizer.nextToken());
            if (right < budgets[i]) {
                right = budgets[i];
            }
        }
        int total = Integer.parseInt(br.readLine());

        int sum = 0;
        while (left <= right) {
            int mid = (left + right) >> 1;

            sum = 0;
            for (int budget : budgets) {
                sum += Math.min(budget, mid);
            }

            if (sum <= total) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        sb.append(right);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}