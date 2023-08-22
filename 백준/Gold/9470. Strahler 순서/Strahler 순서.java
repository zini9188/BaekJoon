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
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            tokenizer = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(tokenizer.nextToken());
            int M = Integer.parseInt(tokenizer.nextToken());
            int P = Integer.parseInt(tokenizer.nextToken());

            ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
            for (int i = 0; i < M + 1; i++) {
                graph.add(new ArrayList<>());
            }

            int[] inDegree = new int[M + 1];
            for (int i = 1; i <= P; i++) {
                tokenizer = new StringTokenizer(br.readLine());
                int A = Integer.parseInt(tokenizer.nextToken());
                int B = Integer.parseInt(tokenizer.nextToken());
                graph.get(A).add(B);
                inDegree[B]++;
            }

            int[][] count = new int[M + 1][M + 1];
            int[] strahler = new int[M + 1];
            Queue<Integer> queue = new ArrayDeque<>();
            for (int i = 1; i < M + 1; i++) {
                if (inDegree[i] == 0) {
                    queue.add(i);
                    strahler[i] = 1;
                }
            }

            int max = 0;
            while (!queue.isEmpty()) {
                int cur = queue.poll();
                if (count[cur][strahler[cur]] >= 2) {
                    strahler[cur]++;
                }
                max = Math.max(max, strahler[cur]);
                for (Integer next : graph.get(cur)) {
                    count[next][strahler[cur]]++;
                    strahler[next] = Math.max(strahler[next], strahler[cur]);
                    if (--inDegree[next] == 0) {
                        queue.add(next);
                    }
                }
            }
            
            sb.append(K).append(" ").append(max).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}