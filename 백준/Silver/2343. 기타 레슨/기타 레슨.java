import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int[] n = new int[N];
        tokenizer = new StringTokenizer(br.readLine());

        int left = 1, right = 0;
        for (int i = 0; i < N; i++) {
            n[i] = Integer.parseInt(tokenizer.nextToken());
            right += n[i];
            left = Math.max(n[i], left);
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            int sum = 0, count = 0;
            for (int i = 0; i < N; i++) {
                if (sum + n[i] > mid) {
                    count++;
                    sum = 0;
                }
                sum += n[i];
            }
            if (sum != 0) {
                count++;
            }
            if (count > M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(left + "");
        bw.flush();
        bw.close();
        br.close();
    }
}