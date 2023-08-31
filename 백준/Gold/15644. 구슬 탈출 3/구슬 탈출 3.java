import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static char[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static boolean[][][][] visited;
    static String answer = "";

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        Bead blue = null, red = null;
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            board[i] = tokenizer.nextToken().toCharArray();
            for (int j = 0; j < M; j++) {
                if (board[i][j] == 'B') {
                    blue = new Bead(i, j);
                }
                if (board[i][j] == 'R') {
                    red = new Bead(i, j);
                }
            }
        }
        int n = bfs(blue, red);
        sb.append(n == -1 ? -1 : n + "\n" + answer);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs(Bead blue, Bead red) {
        Queue<Info> queue = new ArrayDeque<>();
        queue.add(new Info(blue, red, 0, new StringBuilder()));
        visited[blue.x][blue.y][red.x][red.y] = true;

        while (!queue.isEmpty()) {
            Info info = queue.poll();
            Bead b = info.blue;
            Bead r = info.red;
            int count = info.count;
            
            if (count >= 10) {
                return -1;
            }

            for (int dir = 0; dir < 4; dir++) {
                Bead nb = new Bead(b.x, b.y);
                Bead nr = new Bead(r.x, r.y);
                if (!nb.move(dir)) {
                    continue;
                }

                if (!nr.move(dir)) {
                    answer = info.dir.append(getDir(dir)).toString();
                    return count + 1;
                }

                if (nb.equals(nr)) {
                    if (nb.move > nr.move) {
                        nb.back(dir);
                    } else {
                        nr.back(dir);
                    }
                }

                if (visited[nb.x][nb.y][nr.x][nr.y]) continue;
                visited[nb.x][nb.y][nr.x][nr.y] = true;
                queue.add(new Info(new Bead(nb), new Bead(nr), count + 1, new StringBuilder(info.dir).append(getDir(dir))));
            }
        }
        return -1;
    }

    static char getDir(int dir) {
        return dir == 0 ? 'D' :
                dir == 1 ? 'U' :
                        dir == 2 ? 'R' : 'L';
    }

    static class Bead {
        int x;
        int y;
        int move = 0;

        public Bead(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Bead(Bead bead) {
            this.x = bead.x;
            this.y = bead.y;
            this.move = 0;
        }

        boolean move(int dir) {
            while (board[x + dx[dir]][y + dy[dir]] != '#') {
                move++;
                x += dx[dir];
                y += dy[dir];
                if (board[x][y] == 'O') {
                    return false;
                }
            }
            return true;
        }

        void back(int dir) {
            x -= dx[dir];
            y -= dy[dir];
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof Bead)) {
                return false;
            }
            Bead b = (Bead) obj;
            return b.x == this.x && b.y == this.y;
        }
    }

    static class Info {
        Bead blue;
        Bead red;
        int count;
        StringBuilder dir;

        public Info(Bead blue, Bead red, int count, StringBuilder dir) {
            this.blue = blue;
            this.red = red;
            this.count = count;
            this.dir = dir;
        }
    }
}