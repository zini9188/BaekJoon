import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static ArrayList<ArrayList<Road>> roads = new ArrayList<>();
    static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(tokenizer.nextToken());
        int B = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());
        for (int i = 0; i < N + 1; i++) {
            roads.add(new ArrayList<>());
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(tokenizer.nextToken());
            int E = Integer.parseInt(tokenizer.nextToken());
            int L = Integer.parseInt(tokenizer.nextToken());
            roads.get(D).add(new Road(E, L));
            roads.get(E).add(new Road(D, L));
        }

        int[] dist1 = dijkstra(A);
        int[] dist2 = dijkstra(B);
        int[] dist3 = dijkstra(C);

        int max = -1;
        int index = 0;
        for (int i = 1; i < N + 1; i++) {
            if (i == A || i == B || i == C) {
                continue;
            }

            int min = Math.min(Math.min(dist1[i], dist2[i]), dist3[i]);
            if (min > max) {
                max = min;
                index = i;
            }
        }

        bw.write(index + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        PriorityQueue<Road> queue = new PriorityQueue<>();
        dist[start] = 0;
        queue.add(new Road(start, 0));
        while (!queue.isEmpty()) {
            Road current = queue.poll();

            if (dist[current.to] < current.dist) continue;
            for (Road next : roads.get(current.to)) {
                int nextDist = current.dist + next.dist;
                if (dist[next.to] > nextDist) {
                    dist[next.to] = nextDist;
                    queue.add(new Road(next.to, nextDist));
                }
            }
        }

        return dist;
    }

    static class Road implements Comparable<Road> {
        int to, dist;

        public Road(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Road o) {
            if (this.dist == o.dist) {
                return this.to - o.to;
            }
            return this.dist - o.dist;
        }
    }
}