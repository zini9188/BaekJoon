import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static final int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    private static PriorityQueue<Edge> graph = new PriorityQueue<>();
    private static int[][] maze;

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        maze = new int[N][N];
        List<int[]> point = new ArrayList<>();

        int idx = -1;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                maze[i][j] = line.charAt(j);
                if (Character.isDigit(maze[i][j])) {
                    maze[i][j] = maze[i][j] - '0';
                } else {
                    point.add(new int[]{i, j});
                    maze[i][j] = idx--;
                }
            }
        }

        int nodeCount = point.size();

        for (int i = 0; i < point.size(); i++) {
            bfs(i, point.get(i));
        }

        sb.append(calcMove(nodeCount));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int calcMove(int nodeCount) {
        int[] parent = new int[graph.size() + 1];
        for (int i = 0; i <= graph.size(); i++) {
            parent[i] = i;
        }

        int dist = 0;
        int edgeCount = 0;
        while (!graph.isEmpty()) {
            Edge poll = graph.poll();
            if (union(parent, poll.from, poll.to)) {
                dist += poll.cost;
                edgeCount++;
            }
        }

        return edgeCount != nodeCount - 1 ? -1 : dist;
    }

    private static int find(int[] parent, int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent, parent[x]);
    }

    private static boolean union(int[] parent, int x, int y) {
        x = find(parent, x);
        y = find(parent, y);
        if (x == y) {
            return false;
        }
        parent[x] = y;
        return true;
    }

    private static void bfs(int idx, int[] p) {
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{p[0], p[1], 0});
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        visited[p[0]][p[1]] = true;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = poll[0] + dx[i];
                int ny = poll[1] + dy[i];
                if (nx >= 0 && nx < maze.length && ny >= 0 && ny < maze[0].length && !visited[nx][ny]) {
                    if (maze[nx][ny] == 1) {
                        continue;
                    }

                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny, poll[2] + 1});
                    if (maze[nx][ny] <= -1) {
                        int k = -(int) maze[nx][ny] - 1;
                        graph.add(new Edge(idx, k, poll[2] + 1));
                    }
                }
            }
        }
    }

    private static class Edge implements Comparable<Edge> {
        int from, to, cost;

        public Edge(int from, int to, int cost) {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
}