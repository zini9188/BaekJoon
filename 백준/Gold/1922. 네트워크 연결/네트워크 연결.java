import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Connection>> networks = new ArrayList<>();

        for (int i = 0; i <= N; i++) {
            networks.add(new ArrayList<>());
        }

        StringTokenizer tokenizer;
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            networks.get(a).add(new Connection(b, c));
            networks.get(b).add(new Connection(a, c));
        }

        PriorityQueue<Connection> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
        queue.add(new Connection(1, 0));
        boolean[] visited = new boolean[N + 1];
        int answer = 0;
        while (!queue.isEmpty()) {
            Connection current = queue.poll();
            int from = current.to;
            int cost = current.cost;

            if (visited[from]) {
                continue;
            }
            
            visited[from] = true;
            answer += cost;

            for (Connection connection : networks.get(from)) {
                if (!visited[connection.to]) {
                    queue.add(connection);
                }
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static class Connection {
        int to, cost;

        public Connection(int to, int cost) {
            this.to = to;
            this.cost = cost;
        }
    }
}