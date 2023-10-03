import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int F, S, G, U, D;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        F = Integer.parseInt(tokenizer.nextToken());
        S = Integer.parseInt(tokenizer.nextToken());
        G = Integer.parseInt(tokenizer.nextToken());
        U = Integer.parseInt(tokenizer.nextToken());
        D = Integer.parseInt(tokenizer.nextToken());

        sb.append(solution());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String solution() {
        boolean[] visited = new boolean[F + 1];
        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{S, 0});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();

            if (cur[0] == G) {
                return String.valueOf(cur[1]);
            }

            if (cur[0] + U <= F && !visited[cur[0] + U]) {
                visited[cur[0] + U] = true;
                queue.add(new int[]{cur[0] + U, cur[1] + 1});
            }

            if (cur[0] - D >= 1 && !visited[cur[0] - D]) {
                visited[cur[0] - D] = true;
                queue.add(new int[]{cur[0] - D, cur[1] + 1});
            }
        }
        
        return "use the stairs";
    }
}