import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] parents;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        parents = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }

        PriorityQueue<Road> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            int C = Integer.parseInt(tokenizer.nextToken());
            pq.add(new Road(A, B, C));
        }

        int cost = 0;
        int bigCost = 0;
        while (!pq.isEmpty()) {
            Road road = pq.poll();
            if (!union(road.from, road.to)) {
                cost += road.cost;
                bigCost = Math.max(bigCost, road.cost);
            }
        }

        bw.write((cost - bigCost) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean union(int from, int to) {
        int x = find(from);
        int y = find(to);
        if (x == y) return true;
        parents[x] = y;
        return false;
    }

    private static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    static class Road {
        int from;
        int to;
        int cost;

        public Road(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
}