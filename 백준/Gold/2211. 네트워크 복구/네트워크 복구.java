import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    private static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Network network = new Network(N);
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            network.connect(from, to, cost);
        }

        List<Line> answer = network.dijkstra(1);
        sb.append(answer.size()).append("\n");
        for (Line ans : answer) {
            sb.append(ans.from).append(" ").append(ans.to).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Network {

        List<List<Line>> connects;

        public Network(int N) {
            connects = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                connects.add(new ArrayList<>());
            }
        }

        public void connect(int from, int to, int cost) {
            connects.get(from).add(new Line(from, to, cost));
            connects.get(to).add(new Line(to, from, cost));
        }

        public List<Line> dijkstra(int start) {
            boolean[] visited = new boolean[N + 1];
            int[] costs = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                Arrays.fill(costs, Integer.MAX_VALUE);
            }

            List<Line> res = new ArrayList<>();
            Queue<Line> q = new PriorityQueue<>();
            q.add(new Line(start, start, 0));
            visited[start] = true;
            costs[start] = 0;

            while (!q.isEmpty()) {
                Line cur = q.poll();

                if (!visited[cur.to]) {
                    visited[cur.to] = true;
                    res.add(cur);
                }

                for (Line next : connects.get(cur.to)) {
                    if (costs[cur.to] + next.cost < costs[next.to]) {
                        costs[next.to] = costs[cur.to] + next.cost;
                        q.add(new Line(next.from, next.to, costs[next.to]));
                    }
                }
            }
            return res;
        }
    }

    static class Line implements Comparable<Line> {

        int from, to, cost;

        public Line(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Line o) {
            return cost - o.cost;
        }
    }
}