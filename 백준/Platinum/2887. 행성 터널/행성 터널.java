import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        ArrayList<Planet> planets = new ArrayList<>();
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            planets.add(new Planet(i, Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
            graph.add(new ArrayList<>());
        }

        planets.sort(Comparator.comparingInt(o -> o.x));
        for (int i = 0; i < N - 1; i++) {
            graph.get(planets.get(i).idx).add(new Node(planets.get(i + 1).idx, Math.abs(planets.get(i).x - planets.get(i + 1).x)));
            graph.get(planets.get(i + 1).idx).add(new Node(planets.get(i).idx, Math.abs(planets.get(i).x - planets.get(i + 1).x)));
        }

        planets.sort(Comparator.comparingInt(o -> o.y));
        for (int i = 0; i < N - 1; i++) {
            graph.get(planets.get(i).idx).add(new Node(planets.get(i + 1).idx, Math.abs(planets.get(i).y - planets.get(i + 1).y)));
            graph.get(planets.get(i + 1).idx).add(new Node(planets.get(i).idx, Math.abs(planets.get(i).y - planets.get(i + 1).y)));
        }

        planets.sort(Comparator.comparingInt(o -> o.z));
        for (int i = 0; i < N - 1; i++) {
            graph.get(planets.get(i).idx).add(new Node(planets.get(i + 1).idx, Math.abs(planets.get(i).z - planets.get(i + 1).z)));
            graph.get(planets.get(i + 1).idx).add(new Node(planets.get(i).idx, Math.abs(planets.get(i).z - planets.get(i + 1).z)));
        }

        long ans = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(0, 0));
        boolean[] visited = new boolean[N];
        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (visited[cur.to]) {
                continue;
            }

            visited[cur.to] = true;
            ans += cur.cost;

            for (Node next : graph.get(cur.to)) {
                if (!visited[next.to]) {
                    pq.add(next);
                }
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Planet {
        int idx, x, y, z;

        public Planet(int idx, int x, int y, int z) {
            this.idx = idx;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Node implements Comparable<Node> {
        int to, cost;

        public Node(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
}