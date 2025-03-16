import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();
        int M = read();

        Queue<Node> q = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            q.add(new Node(read(), read()));
        }

        int amount = 0;
        int maxCost = 0;
        int cost = 0;
        int ans = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.cost > maxCost) {
                cost = node.cost;
                maxCost = node.cost;
            } else if (node.cost == maxCost) {
                cost += node.cost;
            }

            amount += node.weight;

            if (amount >= M) {
                ans = Math.min(ans, cost);
            }
        }

        if (amount < M) {
            sb.append("-1");
        } else {
            sb.append(ans);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return negative ? -n : n;
    }

    private static class Node implements Comparable<Node> {

        int weight, cost;

        public Node(int weight, int cost) {
            this.weight = weight;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            if (cost == o.cost) {
                return o.weight - weight;
            }

            return cost - o.cost;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "weight=" + weight +
                    ", cost=" + cost +
                    '}';
        }
    }
}