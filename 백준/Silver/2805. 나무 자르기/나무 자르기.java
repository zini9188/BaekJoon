import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static ArrayList<Integer> woods;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        woods = new ArrayList<>();
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            woods.add(Integer.parseInt(tokenizer.nextToken()));
        }
        long result  = solution();
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static long solution() {
        long left = 0, right = Collections.max(woods);
        long mid;
        while (left <= right) {
            mid = (left + right) / 2;
            long sum = 0;
            for (long wood : woods) {
                if (wood - mid > 0) {
                    sum += wood - mid;
                }
            }
            if (sum >= M) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right;
    }
}