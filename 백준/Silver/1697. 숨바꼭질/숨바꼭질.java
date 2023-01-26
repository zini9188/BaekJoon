import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static Queue<int[]> queue;
    static boolean[] visited;
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        visited = new boolean[100001];
        queue = new PriorityQueue<>((Comparator.comparingInt(o -> o[0])));
        queue.add(new int[]{0, N});
        visited[N] = true;

        while (!queue.isEmpty()) {
            int[] info = queue.poll();
            int second = info[0];
            int x = info[1];
            if (x == K) {
                result = Math.min(second, result);
            }

            if (x - 1 >= 0 && !visited[x - 1]) {
                visited[x - 1] = true;
                queue.add(new int[]{second + 1, x - 1});
            }
            if (x + 1 <= 100000 && !visited[x + 1]) {
                visited[x + 1] = true;
                queue.add(new int[]{second + 1, x + 1});
            }
            if (x * 2 <= 100000 && !visited[x * 2]) {
                visited[x * 2] = true;
                queue.add(new int[]{second + 1, x * 2});
            }
        }
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}