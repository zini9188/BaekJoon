import java.io.*;
import java.util.*;

public class Main {

    static final int BOARD_SIZE = 4;
    static final int UP = 1, LEFT = 2, DOWN = 3, RIGHT = 4;
    static final int START_DIR = 1, END_DIR = 8;
    static final int MAX_MOVE = 3;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int M, S;
    static Queue<Fish> cloneFishes;
    static Queue<Fish>[][] board;
    static Queue<Integer>[][] isSmell;
    static boolean[][] visited;
    static Shark shark;
    static int[] dx = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dy = {0, -1, -1, 0, 1, 1, 1, 0, -1};
    static int fastTactic;
    static int maxFishes;
    static List<Point> tactics;
    static List<Point> realTactics;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        M = Integer.parseInt(tokenizer.nextToken());
        S = Integer.parseInt(tokenizer.nextToken());

        board = new Queue[BOARD_SIZE + 1][BOARD_SIZE + 1];
        isSmell = new Queue[BOARD_SIZE + 1][BOARD_SIZE + 1];
        visited = new boolean[BOARD_SIZE + 1][BOARD_SIZE + 1];
        cloneFishes = new ArrayDeque<>();
        for (int i = 1; i < BOARD_SIZE + 1; i++) {
            for (int j = 1; j < BOARD_SIZE + 1; j++) {
                board[i][j] = new ArrayDeque<>();
                isSmell[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokenizer.nextToken());
            int y = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());
            board[x][y].add(new Fish(x, y, d));
        }

        tokenizer = new StringTokenizer(br.readLine());
        int sx = Integer.parseInt(tokenizer.nextToken());
        int sy = Integer.parseInt(tokenizer.nextToken());
        shark = new Shark(sx, sy, UP);

        for (int time = 0; time < S; time++) {
            castCloningMagic();
            moveFishes();
            initShark();
            moveShark(shark.x, shark.y, 0, 0, 0);
            excludeFishes(time);
            removeFishesSmell(time);
            completeCloningMagic();
        }

        int ans = 0;
        for (int i = 1; i <= BOARD_SIZE; i++) {
            for (int j = 1; j <= BOARD_SIZE; j++) {
                if (!board[i][j].isEmpty()) {
                    ans += board[i][j].size();
                }
            }
        }
        sb.append(ans);

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void excludeFishes(int time) {
        for (int i = 0; i < MAX_MOVE; i++) {
            Point p = realTactics.get(i);
            if (!board[p.x][p.y].isEmpty()) {
                isSmell[p.x][p.y].add(time);
                board[p.x][p.y].clear();
            }
        }

        Point nShark = realTactics.get(realTactics.size() - 1);
        shark.x = nShark.x;
        shark.y = nShark.y;
    }

    private static void initShark() {
        fastTactic = Integer.MAX_VALUE;
        maxFishes = 0;
        tactics = new LinkedList<>();
        realTactics = new LinkedList<>();
    }

    private static void removeFishesSmell(int time) {
        if (time < 2) {
            return;
        }

        for (int i = 1; i <= BOARD_SIZE; i++) {
            for (int j = 1; j <= BOARD_SIZE; j++) {
                while (!isSmell[i][j].isEmpty() && time - isSmell[i][j].peek() == 2) {
                    isSmell[i][j].poll();
                }
            }
        }
    }

    private static void moveShark(int x, int y, int cnt, int fast, int excludeFishes) {
        if (cnt == 3) {
            if (excludeFishes > maxFishes) {
                fastTactic = fast;
                maxFishes = excludeFishes;
                realTactics = new LinkedList<>(List.of(tactics.toArray(new Point[0])));
            } else if (excludeFishes == maxFishes && fastTactic > fast) {
                fastTactic = fast;
                realTactics = new LinkedList<>(List.of(tactics.toArray(new Point[0])));
            }
            return;
        }

        for (int dir = 1; dir < END_DIR; dir += 2) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (isInRange(nx, ny)) {
                tactics.add(new Point(nx, ny));
                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    moveShark(nx, ny, cnt + 1, fast * 10 + transDir(dir), excludeFishes + board[nx][ny].size());
                    visited[nx][ny] = false;
                } else {
                    moveShark(nx, ny, cnt + 1, fast * 10 + transDir(dir), excludeFishes);
                }
                tactics.remove(tactics.size() - 1);
            }
        }
    }

    private static int transDir(int dir) {
        if (dir == 1) {
            return LEFT;
        }
        if (dir == 3) {
            return UP;
        }
        if (dir == 5) {
            return RIGHT;
        }
        return DOWN;
    }

    private static void moveFishes() {
        Queue<Fish> fishes = new ArrayDeque<>();
        for (int i = 1; i <= BOARD_SIZE; i++) {
            for (int j = 1; j <= BOARD_SIZE; j++) {
                if (!board[i][j].isEmpty()) {
                    int size = board[i][j].size();
                    for (int repeat = 0; repeat < size; repeat++) {
                        Fish fish = board[i][j].poll();
                        fishes.add(moveFish(fish));
                    }
                }
            }
        }

        while (!fishes.isEmpty()) {
            Fish fish = fishes.poll();
            board[fish.x][fish.y].add(fish);
        }
    }

    private static Fish moveFish(Fish fish) {
        int d = fish.d;
        for (int dir = START_DIR; dir <= END_DIR; dir++) {
            int nd = d;
            int nx = fish.x + dx[nd];
            int ny = fish.y + dy[nd];
            if (isInRange(nx, ny) && isSmell[nx][ny].isEmpty() && !isSharkArea(nx, ny)) {
                return new Fish(nx, ny, nd);
            }
            d = d == START_DIR ? END_DIR : d - 1;
        }
        return fish;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 1 && y >= 1 && x <= BOARD_SIZE && y <= BOARD_SIZE;
    }

    private static boolean isSharkArea(int x, int y) {
        return shark.x == x && shark.y == y;
    }


    private static void completeCloningMagic() {
        while (!cloneFishes.isEmpty()) {
            Fish fish = cloneFishes.poll();
            board[fish.x][fish.y].add(fish);
        }
    }

    private static void castCloningMagic() {
        for (int i = 1; i <= BOARD_SIZE; i++) {
            for (int j = 1; j <= BOARD_SIZE; j++) {
                if (!board[i][j].isEmpty() && !(board[i][j] instanceof Shark)) {
                    cloneFishes.addAll(List.of(board[i][j].toArray(new Fish[0]).clone()));
                }
            }
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Fish {
        int x, y, d;

        public Fish(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static class Shark extends Fish {

        public Shark(int x, int y, int d) {
            super(x, y, d);
        }
    }
}