import java.io.*;
import java.util.*;

public class Main {

    static final int INF = 100000 * 1000 + 1;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        List<List<Bus>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            int cost = Integer.parseInt(tokenizer.nextToken());
            graph.get(from).add(new Bus(to, cost));
        }

        tokenizer = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(tokenizer.nextToken());
        int e = Integer.parseInt(tokenizer.nextToken());

        int[] record = new int[n + 1];
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);
        dist[s] = 0;

        Queue<Bus> pq = new PriorityQueue<>();
        pq.add(new Bus(s, 0));
        while (!pq.isEmpty()) {
            Bus cur = pq.poll();

            if (dist[cur.pos] < cur.cost) {
                continue;
            }

            for (Bus next : graph.get(cur.pos)) {
                if (dist[next.pos] > dist[cur.pos] + next.cost) {
                    dist[next.pos] = dist[cur.pos] + next.cost;
                    record[next.pos] = cur.pos;
                    pq.add(new Bus(next.pos, dist[next.pos]));
                }
            }
        }

        sb.append(dist[e]).append("\n");
        Stack<Integer> st = new Stack<>();
        st.push(e);
        int cnt = 1;
        while (record[e] != 0) {
            cnt++;
            st.push(record[e]);
            e = record[e];
        }

        sb.append(cnt).append("\n");
        while (!st.isEmpty()) {
            sb.append(st.pop()).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Bus implements Comparable<Bus> {
        int pos, cost;

        public Bus(int pos, int cost) {
            this.pos = pos;
            this.cost = cost;
        }

        @Override
        public int compareTo(Bus o) {
            return cost - o.cost;
        }
    }
}