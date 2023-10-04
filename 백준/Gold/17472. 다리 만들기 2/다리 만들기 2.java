import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] country;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int idx;
    static boolean[] connected = new boolean[7];
    static ArrayList<Island> nodes = new ArrayList<>();
    static ArrayList<ArrayList<Node>> graph = new ArrayList<>();
    static PriorityQueue<Node> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i <= 6; i++) {
            graph.add(new ArrayList<>());
        }

        country = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                country[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        makeIsland();
        findDist();
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            graph.get(cur.from).add(new Node(0, cur.to, cur.dist));
        }

        pq.add(new Node(0, 1, 0));
        int length = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (connected[cur.to]) {
                continue;
            }
            connected[cur.to] = true;
            length += cur.dist;
            count++;

            for (Node next : graph.get(cur.to)) {
                if (!connected[next.to]) {
                    pq.add(next);
                }
            }
        }

        sb.append((count != idx || length == 0) ? -1 : length);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void findDist() {
        for (Island cur : nodes) {
            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x;
                int ny = cur.y;
                int cnt = 0;
                while (isInRange(nx + dx[dir], ny + dy[dir])) {
                    nx += dx[dir];
                    ny += dy[dir];

                    if (country[nx][ny] == cur.idx) {
                        break;
                    }

                    if (country[nx][ny] == 0) {
                        cnt++;
                    } else if (cnt > 1) {
                        pq.add(new Node(cur.idx, country[nx][ny], cnt));
                        break;
                    } else {
                        break;
                    }
                }
            }
        }
    }

    private static void makeIsland() {
        boolean[][] visited = new boolean[N][M];
        idx = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                if (!visited[x][y] && country[x][y] == 1) {
                    idx++;
                    nodes.add(new Island(idx, x, y));
                    visited[x][y] = true;
                    Queue<Island> queue = new ArrayDeque<>();
                    queue.add(new Island(idx, x, y));
                    country[x][y] = idx;
                    while (!queue.isEmpty()) {
                        Island island = queue.poll();
                        for (int dir = 0; dir < 4; dir++) {
                            int nx = island.x + dx[dir];
                            int ny = island.y + dy[dir];

                            if (isInRange(nx, ny) && !visited[nx][ny] && country[nx][ny] == 1) {
                                nodes.add(new Island(idx, nx, ny));
                                visited[nx][ny] = true;
                                country[nx][ny] = idx;
                                queue.add(new Island(idx, nx, ny));
                            }
                        }
                    }
                }
            }
        }
    }

    static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    static class Island {
        int idx, x, y;

        public Island(int idx, int x, int y) {
            this.idx = idx;
            this.x = x;
            this.y = y;
        }
    }

    static class Node implements Comparable<Node> {
        int from, to, dist;

        public Node(int from, int to, int dist) {
            this.from = from;
            this.to = to;
            this.dist = dist;
        }

        @Override
        public int compareTo(Node o) {
            return this.dist - o.dist;
        }
    }
}