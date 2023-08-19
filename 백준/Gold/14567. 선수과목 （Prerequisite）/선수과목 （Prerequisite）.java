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
        int[] semester = new int[N + 1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            graph.get(A).add(B);
            possible[B]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++) {
            if (possible[i] == 0) {
                queue.add(i);
                semester[i] = 1;
            }
        }

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (Integer next : graph.get(current)) {
                if (--possible[next] == 0) {
                    queue.add(next);
                    semester[next] = semester[current] + 1;
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
            sb.append(semester[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}