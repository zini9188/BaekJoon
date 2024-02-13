import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a < b) {
                graph.get(a).add(new Node(b, c));
            }
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        int ans = 0;
        int[][] dist = new int[N + 1][N + 1];
        int repeat = 1;
        while (!q.isEmpty() && repeat < M) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int cur = q.poll();

                for (Node next : graph.get(cur)) {
                    int val = dist[repeat][cur] + next.val;

                    if (val <= dist[repeat + 1][next.to]) {
                        continue;
                    }

                    dist[repeat + 1][next.to] = val;
                    q.add(next.to);
                }
            }
            repeat++;
        }

        for (int i = 2; i <= M; i++) {
            if (ans < dist[i][N]) {
                ans = dist[i][N];
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Node {

        int to, val;

        public Node(int to, int val) {
            this.to = to;
            this.val = val;
        }
    }
}