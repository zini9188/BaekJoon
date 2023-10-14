import java.io.*;
import java.util.*;

public class Main {

    static final int DOWN = 4;
    static final int UP = 3;
    static final int LEFT = 2;
    static final int RIGHT = 1;
    static final int TEMP = 5;
    static int chocolate = 0;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer tokenizer;
    static int[][] map;
    static Wall[][] walls;
    static ArrayList<Machine> machines;
    static ArrayList<Point> searches;
    static int R, C, K;
    static int[] dx = { 0, 0, 0, -1, 1 };
    static int[] dy = { 0, 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        input();
        simulation();
        System.out.println(chocolate);
    }

    private static void input() throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        machines = new ArrayList<>();
        searches = new ArrayList<>();
        map = new int[R + 1][C + 1];
        walls = new Wall[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                int n = Integer.parseInt(tokenizer.nextToken());
                if (n == 1) {
                    machines.add(new Machine(i, j, RIGHT));
                } else if (n == 2) {
                    machines.add(new Machine(i, j, LEFT));
                } else if (n == 3) {
                    machines.add(new Machine(i, j, UP));
                } else if (n == 4) {
                    machines.add(new Machine(i, j, DOWN));
                } else if (n == 5) {
                    searches.add(new Point(i, j));
                }
                walls[i][j] = new Wall();
            }
        }

        // K개의 벽을 입력
        int W = Integer.parseInt(br.readLine());
        for (int i = 0; i < W; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int t = Integer.parseInt(tokenizer.nextToken());

            // 0인 경우 x,y, x-1,y에 벽
            if (t == 0) {
                walls[x][y].isBlock[UP] = true;
                walls[x - 1][y].isBlock[DOWN] = true;
            } else {
                walls[x][y].isBlock[RIGHT] = true;
                walls[x][y + 1].isBlock[LEFT] = true;
            }
        }
    }

    private static void simulation() {
        do {
            // 1. 집에 있는 모든 온풍기에서 바람
            wind();
            // 2. 온도가 조절
            second();
            // 3. 온도가 1 이상인 가장 바깥쪽 칸의 온도가 1씩 감소
            third();
            // 4. 초콜릿 하나 먹기
            chocolate++;
            if (chocolate > 100) {
                break;
            }
            // 5. 조사하는 모든 칸의 온도가 K 이상이 되었는지 검사
        } while (search());
    }

    private static void second() {
        // 온도를 조절한다.
        int[][] tmp = new int[R + 1][C + 1];
        for (int x = 1; x <= R; x++) {
            for (int y = 1; y <= C; y++) {
                // 모든 칸에 대해
                for (int d = 1; d <= 4; d++) {
                    int nx = x + dx[d];
                    int ny = y + dy[d];

                    if (isOut(nx, ny) || map[x][y] <= map[nx][ny]) {
                        continue;
                    }

                    if (walls[x][y] != null && walls[x][y].isBlock[d]) {
                        continue;
                    }

                    // 두 칸의 온도 차이/4
                    int n = (map[x][y] - map[nx][ny]) / 4;
                    tmp[x][y] -= n;
                    tmp[nx][ny] += n;
                }
            }
        }
        for (int x = 1; x <= R; x++) {
            for (int y = 1; y <= C; y++) {
                map[x][y] += tmp[x][y];
            }
        }
    }

    private static void third() {
        // 열이 증가
        for (int i = 1; i <= C; i++) {
            // 1행, R행에 대해 --
            if (map[1][i] > 0) {
                map[1][i]--;
            }
            if (map[R][i] > 0) {
                map[R][i]--;
            }
        }

        // 행이 증가
        for (int i = 2; i <= R - 1; i++) {
            // 1열, C열에 대해 --
            if (map[i][1] > 0) {
                map[i][1]--;
            }
            if (map[i][C] > 0) {
                map[i][C]--;
            }
        }
    }

    private static void print() {
        for (int i = 1; i <= R; i++) {
            for (int j = 1; j <= C; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void wind() {
        // 온풍기 돌면서 바람 쏘기
        for (int i = 0; i < machines.size(); i++) {
            Machine machine = machines.get(i);
            int dir = machine.d;

            // 온풍기 바로 앞 좌표
            int x = machine.x + dx[dir];
            int y = machine.y + dy[dir];

            // 해당 좌표가 막혔는지 확인
            if (isOut(x, y)) {
                continue;
            }

            if (dir == LEFT || dir == RIGHT) {
                windLR(x, y, dir);
            } else {
                windUD(x, y, dir);
            }
        }
    }

    private static void windLR(int x, int y, int dir) {
        boolean[][] visited = new boolean[R + 1][C + 1];
        // 온풍기는 5부터 시작
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        int temp = TEMP;
        map[x][y] += temp;
        for (int j = 0; j < 4; j++) {
            // 한 번 돌때마다 줄이기
            temp--;
            if (queue.isEmpty()) {
                continue;
            }
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Point cur = queue.poll();

                int nx;
                int ny = cur.y + dy[dir];
                // UP = 3, DOWN = 4, LEFT = 2 -> 위 아래 제자리
                for (int d = 2; d <= 4; d++) {
                    nx = cur.x + dx[d];
                    if (isOut(nx, ny) || visited[nx][ny]) {
                        continue;
                    }

                    if (walls[nx][ny].isBlock[getDir(dir)]) {
                        continue;
                    }

                    // 아래로 퍼지려면 아래를 체크
                    if (d == DOWN && walls[cur.x][cur.y].isBlock[DOWN]) {
                        continue;
                    }

                    if (d == UP && walls[cur.x][cur.y].isBlock[UP]) {
                        continue;
                    }

                    // 다음 위치에 온도 올리기
                    map[nx][ny] += temp;
                    // 방문처리 해주기
                    visited[nx][ny] = true;
                    // 큐에 담기
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }

    private static void windUD(int x, int y, int dir) {
        boolean[][] visited = new boolean[R + 1][C + 1];
        // 온풍기는 5부터 시작
        Queue<Point> queue = new ArrayDeque<>();
        queue.add(new Point(x, y));
        visited[x][y] = true;
        int temp = TEMP;
        map[x][y] += temp;
        for (int j = 0; j < 4; j++) {
            // 한 번 돌때마다 줄이기
            temp--;
            if (queue.isEmpty()) {
                continue;
            }
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                Point cur = queue.poll();

                int nx = cur.x + dx[dir];
                int ny;
                // UP = 3, LEFT = 2, RIGHT = 1 -> 제자리 왼 오
                for (int d = 1; d <= 3; d++) {
                    ny = cur.y + dy[d];
                    if (isOut(nx, ny) || visited[nx][ny]) {
                        continue;
                    }

                    // 갈 좌표에 이동 방향에 반대되는 곳에 벽이 있는지 체크해야 함.
                    if (walls[nx][ny].isBlock[getDir(dir)]) {
                        continue;
                    }

                    // 아래로 퍼지려면 아래를 체크
                    if (d == LEFT && walls[cur.x][cur.y].isBlock[LEFT]) {
                        continue;
                    }

                    if (d == RIGHT && walls[cur.x][cur.y].isBlock[RIGHT]) {
                        continue;
                    }

                    // 다음 위치에 온도 올리기
                    map[nx][ny] += temp;
                    // 방문처리 해주기
                    visited[nx][ny] = true;
                    // 큐에 담기
                    queue.add(new Point(nx, ny));
                }
            }
        }
    }

    private static int getDir(int dir) {
        return dir == UP ? DOWN : dir == LEFT ? RIGHT : dir == RIGHT ? LEFT : UP;
    }

    private static boolean isOut(int x, int y) {
        return x < 1 || y < 1 || x > R || y > C;
    }

    private static boolean search() {
        // 모든 조사 지점을 돌면서 K 이상인지 테스트한다.
        int size = searches.size();
        for (int i = 0; i < size; i++) {
            Point p = searches.get(i);
            if (map[p.x][p.y] < K) {
                return true;
            }
        }
        return false;
    }

    static class Machine {
        int x, y, d;

        public Machine(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Wall {
        boolean[] isBlock;

        public Wall() {
            isBlock = new boolean[5];
        }
    }
}