import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            ArrayList<ArrayList<Connection>> network = new ArrayList<>();
            for (int computer = 0; computer <= n; computer++) {
                network.add(new ArrayList<>());
            }

            for (int j = 0; j < d; j++) {
                tokenizer = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                int s = Integer.parseInt(tokenizer.nextToken());
                network.get(b).add(new Connection(a, s));
            }

            PriorityQueue<Connection> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));
            queue.add(new Connection(c, 0));
            boolean[] visited = new boolean[n + 1];
            int infection = 0;
            int totalTime = 0;
            while (!queue.isEmpty()) {
                Connection current = queue.poll();
                int from = current.to;

                if (visited[from]) {
                    continue;
                }

                visited[from] = true;
                infection++;
                totalTime = current.time;

                for (Connection next : network.get(from)) {
                    if (!visited[next.to]) {
                        queue.add(new Connection(next.to, next.time + current.time));
                    }
                }
            }

            builder.append(infection).append(" ").append(totalTime).append("\n");
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Connection {
        int to, time;

        public Connection(int to, int time) {
            this.to = to;
            this.time = time;
        }
    }
}