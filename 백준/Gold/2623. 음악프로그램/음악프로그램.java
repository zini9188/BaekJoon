import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] possible = new int[N + 1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int a = Integer.parseInt(tokenizer.nextToken());
            for (int j = 1; j < n; j++) {
                int b = Integer.parseInt(tokenizer.nextToken());
                possible[b]++;
                graph.get(a).add(b);
                a = b;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++) {
            if (possible[i] == 0) {
                queue.add(i);
            }
        }

        ArrayList<Integer> answer = new ArrayList<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            answer.add(cur);
            for (int next : graph.get(cur)) {
                if (--possible[next] == 0) {
                    queue.add(next);
                }
            }
        }

        if (answer.size() != N) {
            sb.append("0");
        } else {
            for (Integer ans : answer) {
                sb.append(ans).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}