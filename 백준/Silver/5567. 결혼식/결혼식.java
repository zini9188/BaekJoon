import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        int ans = 0;
        for (int i = 0; i < 2 && !q.isEmpty(); i++) {
            int size = q.size();
            for (int j = 0; j < size; j++) {
                int cur = q.poll();

                for (Integer next : graph.get(cur)) {
                    if (!visited[next]) {
                        visited[next] = true;
                        q.add(next);
                        ans++;
                    }
                }
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}