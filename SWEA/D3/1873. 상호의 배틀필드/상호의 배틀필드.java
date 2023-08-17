import java.io.*;
import java.util.StringTokenizer;

public class Solution {

    static final char FLAT = '.';
    static final char BRICK_WALL = '*';
    static final char IRON_WALL = '#';
    static final char UP = '^';
    static final char DOWN = 'v';
    static final char LEFT = '<';
    static final char RIGHT = '>';
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int H, W;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] gameMap;
    static Tank tank;
    static char[] command;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase < T + 1; testCase++) {
            init();

            solution();

            appendResult(testCase);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void appendResult(int testCase) {
        sb.append("#").append(testCase).append(" ");
        for (int x = 0; x < H; x++) {
            for (int y = 0; y < W; y++) {
                sb.append(gameMap[x][y]);
            }
            sb.append("\n");
        }
    }

    private static void solution() {
        for (char cmd : command) {
            if (cmd == 'S') {
                Shell shell = new Shell(tank);
                while (shell.move()) {
                }
            } else {
                tank.move(cmd);
            }
        }
    }

    private static void init() throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        H = Integer.parseInt(tokenizer.nextToken());
        W = Integer.parseInt(tokenizer.nextToken());

        gameMap = new char[H][W];
        for (int x = 0; x < H; x++) {
            gameMap[x] = br.readLine().toCharArray();
            for (int y = 0; y < W; y++) {
                char cur = gameMap[x][y];
                if (cur == UP || cur == DOWN || cur == LEFT || cur == RIGHT) {
                    tank = new Tank(new Point(x, y), cur);
                }
            }
        }

        br.readLine();
        command = br.readLine().toCharArray();
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point move(char dir) {
            return new Point(x + dx[matchDir(dir)], y + dy[matchDir(dir)]);
        }

        public boolean isInRange() {
            return x >= 0 && y >= 0 && x < H && y < W;
        }

        public boolean isBrickWall() {
            return gameMap[x][y] == BRICK_WALL;
        }

        public boolean isIronWall() {
            return gameMap[x][y] == IRON_WALL;
        }

        public boolean isFlat() {
            return gameMap[x][y] == FLAT;
        }

        private int matchDir(char dir) {
            if (dir == UP) {
                return 0;
            }
            if (dir == DOWN) {
                return 1;
            }
            if (dir == LEFT) {
                return 2;
            }
            return 3;
        }
    }

    static class Tank {
        Point pos;
        char dir;

        public Tank(Point pos, char dir) {
            this.pos = pos;
            this.dir = dir;
        }

        public void move(char cmd) {
            dir = turnDir(cmd);
            Point np = pos.move(dir);
            if (np.isInRange() && np.isFlat()) {
                gameMap[pos.x][pos.y] = '.';
                pos = np;
            }
            gameMap[pos.x][pos.y] = dir;
        }

        private char turnDir(char cmd) {
            if (cmd == 'U') {
                return UP;
            }
            if (cmd == 'D') {
                return DOWN;
            }
            if (cmd == 'L') {
                return LEFT;
            }
            return dir = RIGHT;
        }

        public Point shoot() {
            return this.pos;
        }
    }

    static class Shell {
        Point pos;
        char dir;

        public Shell(Tank tank) {
            this.pos = tank.shoot();
            this.dir = tank.dir;
        }

        public boolean move() {
            Point np = pos.move(dir);
            if (np.isInRange()) {
                if (np.isIronWall()) {
                    return false;
                }
                if (np.isBrickWall()) {
                    gameMap[np.x][np.y] = FLAT;
                    return false;
                }
                pos = np;
                return true;
            }
            return false;
        }
    }

    static class Constant {
    }
}