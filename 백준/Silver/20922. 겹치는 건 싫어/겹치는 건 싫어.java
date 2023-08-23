import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[] arr = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] count = new int[100001];
        Queue<Integer> queue = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < N; i++) {
            queue.add(arr[i]);
            if (++count[arr[i]] <= K) {
                max = Math.max(queue.size(), max);
            }

            while (count[arr[i]] > K) {
                if (!queue.isEmpty()) {
                    count[queue.poll()]--;
                }
            }
        }

        sb.append(max);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
