import java.io.*;
import java.util.*;

public class Main {

    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] board;
    static Point shark;
    static Queue<Magic> castings;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[] beadCount;

    public static void main(String[] args) throws IOException {
        init();
        castMagic();
        sb.append(beadCount[1] + beadCount[2] * 2 + beadCount[3] * 3);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void castMagic() {
        for (int i = 0; i < M; i++) {
            casting();
        }
    }

    private static void moveBead() {
        Point cp = new Point(shark.x, shark.y, LEFT);
        int count = 1;
        Queue<Integer> num = new LinkedList<>();
        while (cp.isInRange()) {
            for (int repeat = 0; repeat < 2 && cp.isInRange(); repeat++) {
                for (int i = 0; i < count && cp.isInRange(); i++) {
                    cp.move();
                    if (cp.getNumber() != 0) {
                        num.add(cp.getNumber());
                        cp.destroyBead();
                    }
                }
                cp.changeDir();
            }
            count++;
        }

        cp = new Point(shark.x, shark.y, LEFT);
        count = 1;
        while (true) {
            for (int repeat = 0; repeat < 2; repeat++) {
                for (int i = 0; i < count; i++) {
                    cp.move();
                    if (!cp.isInRange() || num.isEmpty()) {
                        return;
                    }
                    cp.setBead(num.poll());
                }
                cp.changeDir();
            }
            count++;
        }
    }

    private static void casting() {
        Magic cast = castings.poll();
        Point cp = new Point(shark.x, shark.y, cast.d);
        for (int i = 0; i < cast.s; i++) {
            cp.move();
            if (cp.isInRange()) {
                cp.destroyBead();
            }
        }
        moveBead();
        while (explosion()) {
            moveBead();
        }
        changeBead();
    }

    private static void changeBead() {
        Point cp = new Point(shark.x, shark.y, LEFT);
        int count = 1;
        Queue<Integer> beads = new ArrayDeque<>();
        Bead bead = new Bead(-1);
        while (cp.isInRange()) {
            for (int repeat = 0; repeat < 2 && cp.isInRange(); repeat++) {
                for (int i = 0; i < count && cp.isInRange(); i++) {
                    cp.move();
                    if (cp.getNumber() != 0) {
                        int num = cp.getNumber();
                        if (bead.num == -1) {
                            bead = new Bead(num);
                        } else if (bead.num == num) {
                            bead.count++;
                        } else {
                            beads.add(bead.count);
                            beads.add(bead.num);
                            bead = new Bead(num);
                        }
                        cp.setBead(0);
                    } else {
                        if (bead.num >= 1) {
                            beads.add(bead.count);
                            beads.add(bead.num);
                        }
                        cp = new Point(shark.x, shark.y, LEFT);
                        count = 1;
                        while (cp.isInRange() && !beads.isEmpty()) {
                            for (int k = 0; k < 2 && cp.isInRange(); k++) {
                                for (int j = 0; j < count && cp.isInRange(); j++) {
                                    cp.move();
                                    if (!beads.isEmpty() && cp.isInRange()) {
                                        cp.setBead(beads.poll());
                                    }
                                }
                                cp.changeDir();
                            }
                            count++;
                        }
                        beads.clear();
                        return;
                    }
                }
                cp.changeDir();
            }
            count++;
        }
    }

    private static boolean explosion() {
        Point cp = new Point(shark.x, shark.y, LEFT);
        int count = 1;
        boolean flag = false;
        while (cp.isInRange()) {
            for (int repeat = 0; repeat < 2 && cp.isInRange(); repeat++) {
                for (int i = 0; i < count && cp.isInRange(); i++) {
                    cp.move();
                    if (cp.canExplosion()) {
                        cp.explode();
                        flag = true;
                    }
                }
                cp.changeDir();
            }
            count++;
        }
        return flag;
    }

    private static void init() throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        board = new int[N + 1][N + 1];
        shark = new Point((N + 1) / 2, (N + 1) / 2, LEFT);
        beadCount = new int[4];

        for (int x = 1; x < N + 1; x++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int y = 1; y < N + 1; y++) {
                board[x][y] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        castings = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(tokenizer.nextToken());
            int s = Integer.parseInt(tokenizer.nextToken());
            castings.add(new Magic(d - 1, s));
        }
    }

    static class Point {
        int x, y, dir;
        Stack<Point> stack = new Stack<>();

        public Point(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public boolean isInRange() {
            return x >= 1 && y >= 1 && x <= N && y <= N;
        }

        public void move() {
            this.x += dx[this.dir];
            this.y += dy[this.dir];
        }

        public boolean canExplosion() {
            if (stack.isEmpty() || stack.peek().getNumber() == this.getNumber()) {
                stack.add(new Point(this.x, this.y, this.dir));
                return false;
            } else if (stack.peek().getNumber() != this.getNumber()) {
                if (stack.size() >= 4) {
                    return true;
                }
                stack.clear();
                stack.add(new Point(this.x, this.y, this.dir));
            }
            return false;
        }

        public void explode() {
            while (!stack.isEmpty()) {
                Point p = stack.pop();
                beadCount[p.getNumber()]++;
                p.destroyBead();
            }
        }

        public void changeDir() {
            if (this.dir == LEFT) {
                this.dir = DOWN;
            } else if (this.dir == DOWN) {
                this.dir = RIGHT;
            } else if (this.dir == RIGHT) {
                this.dir = UP;
            } else {
                this.dir = LEFT;
            }
        }

        public int getNumber() {
            return board[this.x][this.y];
        }

        public void destroyBead() {
            board[this.x][this.y] = 0;
        }

        public void setBead(int num) {
            board[this.x][this.y] = num;
        }
    }

    static class Magic {
        int d, s;

        public Magic(int d, int s) {
            this.d = d;
            this.s = s;
        }
    }

    static class Bead {
        int num;
        int count;

        public Bead(int num) {
            this.num = num;
            count = 1;
        }
    }
}