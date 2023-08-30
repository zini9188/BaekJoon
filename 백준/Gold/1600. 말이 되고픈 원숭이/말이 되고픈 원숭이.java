import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, 1, 0, -1};
    private static final int[] hx = {1, 2, -1, 2, -2, 1, -2, -1};
    private static final int[] hy = {2, 1, 2, -1, 1, -2, -1, -2};
    private static int K, W, H;
    private static int[][] A;
    private static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());

        A = new int[H][W];
        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < W; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = bfs();
        System.out.println(answer);
    }

    private static int bfs() {
        Queue<Node> q = new ArrayDeque<>();
        int[][][] visited = new int[H][W][40];
        q.add(new Node(0, 0, 0));
        visited[0][0][0] = 1;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            if (cur.x == H - 1 && cur.y == W - 1) {
                return visited[cur.x][cur.y][cur.k] - 1;
            }

            for (int i = 0; i < 8; i++) {
                int nx = cur.x + hx[i];
                int ny = cur.y + hy[i];
                int nk = cur.k + 1;

                if (!inRange(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny][nk] != 0 || A[nx][ny] == 1 || nk > K)
                    continue;

                visited[nx][ny][nk] = visited[cur.x][cur.y][cur.k] + 1;
                q.add(new Node(nx, ny, nk));
            }
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (!inRange(nx, ny)) {
                    continue;
                }
                if (visited[nx][ny][cur.k] != 0 || A[nx][ny] == 1) {
                    continue;
                }
                visited[nx][ny][cur.k] = visited[cur.x][cur.y][cur.k] + 1;
                q.add(new Node(nx, ny, cur.k));
            }
        }
        return -1;
    }

    private static boolean inRange(int x, int y) {
        return x >= 0 && x < H && y >= 0 && y < W;
    }

    private static class Node {
        int x, y, k;

        public Node(int x, int y, int k) {
            this.x = x;
            this.y = y;
            this.k = k;
        }
    }
}