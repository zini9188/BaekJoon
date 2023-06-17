import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static final int HORIZONTAL = 0, VERTICAL = 1, DIAGONAL = 2, WALL = 1;
    static int[][] house;
    static int[] dx = {0, 1, 1};
    static int[] dy = {1, 0, 1};
    static int N;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        house = new int[N + 1][N + 1];
        for (int x = 1; x <= N; x++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            for (int y = 1; y <= N; y++) {
                house[x][y] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        Pipe pipe = new Pipe(HORIZONTAL, new Point(1, 1), new Point(1, 2));
        findPath(pipe);

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    public static void findPath(Pipe pipe) {
        if (pipe.isDestination()) {
            answer++;
            return;
        }

        for (int dir = 0; dir < 3; dir++) {
            if (pipe.checkDirection(dir)) {
                Point nextEndPoint = pipe.end.nextPoint(dir);
                if (dir == DIAGONAL) {
                    if (pipe.end.checkDiagonal()) {
                        Pipe nextPipe = pipe.move(nextEndPoint, dir);
                        findPath(nextPipe);
                    }
                } else {
                    if (nextEndPoint.isInRange() && nextEndPoint.isNotWall()) {
                        Pipe nextPipe = pipe.move(nextEndPoint, dir);
                        findPath(nextPipe);
                    }
                }
            }
        }
    }

    static class Pipe {
        int direction;
        Point front, end;

        public Pipe(int direction, Point front, Point end) {
            this.direction = direction;
            this.front = front;
            this.end = end;
        }

        public Pipe move(Point point, int dir) {
            return new Pipe(dir, end, point);
        }

        public boolean checkDirection(int dir) {
            if (direction == HORIZONTAL) {
                return dir != VERTICAL;
            }
            if (direction == VERTICAL) {
                return dir != HORIZONTAL;
            }
            return true;
        }

        public boolean isDestination() {
            return end.x == N && end.y == N;
        }
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point nextPoint(int dir) {
            return new Point(x + dx[dir], y + dy[dir]);
        }

        public boolean isInRange() {
            return x <= N && y <= N;
        }

        public boolean isNotWall() {
            return house[x][y] != WALL;
        }

        public boolean checkDiagonal() {
            return x + 1 <= N && y + 1 <= N
                    && nextPoint(HORIZONTAL).isNotWall()
                    && nextPoint(VERTICAL).isNotWall()
                    && nextPoint(DIAGONAL).isNotWall();
        }
    }
}