import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int[][] board;
    static boolean[][] visited;
    static Cloud[] commands;

    static class Cloud {
        int d, s;

        public Cloud(int d, int s) {
            this.d = d;
            this.s = s;
        }
    }

    static Queue<Cloud> originClouds;
    static Queue<Cloud> movedClouds;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        originClouds = new LinkedList<>();
        movedClouds = new LinkedList<>();
        board = new int[N + 1][N + 1];
        visited = new boolean[N + 1][N + 1];
        commands = new Cloud[M];
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(tokenizer.nextToken());
            int count = Integer.parseInt(tokenizer.nextToken());
            commands[i] = new Cloud(direction, count);
        }

        solution();
        bw.write(amountOfWater() + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        initRain();
        for (Cloud command : commands) {
            int dir = command.d;
            int count = command.s;
            moveClouds(dir, count);
            useCopyBug();
            addCloud();
        }
    }

    private static void initRain() {
        originClouds.add(new Cloud(N, 1));
        originClouds.add(new Cloud(N, 2));
        originClouds.add(new Cloud(N - 1, 1));
        originClouds.add(new Cloud(N - 1, 2));
    }

    private static void moveClouds(int direction, int count) {
        while (!originClouds.isEmpty()) {
            Cloud data = originClouds.poll();
            Cloud nextData = moveCloud(data, direction, count);
            board[nextData.d][nextData.s]++;
            movedClouds.add(nextData);
        }
    }

    private static Cloud moveCloud(Cloud point, int dir, int count) {
        int x = point.d;
        int y = point.s;
        for (int i = 0; i < count; i++) {
            x = move(x, dx[dir]);
            y = move(y, dy[dir]);
        }
        return new Cloud(x, y);
    }

    private static int move(int pos, int movePoint) {
        pos += movePoint;
        if (pos < 1) return N;
        if (pos > N) return 1;
        return pos;
    }

    private static void useCopyBug() {
        while (!movedClouds.isEmpty()) {
            Cloud data = movedClouds.poll();
            int x = data.d;
            int y = data.s;
            visited[x][y] = true;
            for (int i = 2; i <= 8; i += 2) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (!outOfRange(nx, ny) && board[nx][ny] > 0) {
                    board[x][y]++;
                }
            }
        }
    }

    private static boolean outOfRange(int x, int y) {
        return x <= 0 || y <= 0 || x > N || y > N;
    }

    private static void addCloud() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (board[i][j] >= 2 && !visited[i][j]) {
                    originClouds.add(new Cloud(i, j));
                    board[i][j] -= 2;
                } else if (visited[i][j]) {
                    visited[i][j] = false;
                }
            }
        }
    }

    private static int amountOfWater() {
        int result = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                result += board[i][j];
            }
        }
        return result;
    }
}