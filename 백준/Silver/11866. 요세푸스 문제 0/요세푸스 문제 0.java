import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int K;
    static StringBuilder stringBuilder;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        solution(N, K);

        bw.write(stringBuilder.toString());

        bw.flush();
        bw.close();
        br.close();
    }

    private static StringBuilder solution(int N, int K) {
        Queue<Integer> queue = new LinkedList<>();
        stringBuilder = new StringBuilder("<");
        for (int i = 1; i <= N; i++) queue.add(i);

        while (!queue.isEmpty()) {
            for (int i = 1; i < K; i++) queue.add(queue.poll());
            stringBuilder.append(queue.poll());
            if (!queue.isEmpty()) stringBuilder.append(", ");
        }
        stringBuilder.append(">");
        return stringBuilder;
    }
}