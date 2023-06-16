import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int RAINBOW = 0;
    static final int EMPTY = -2;
    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        autoPlay();

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void autoPlay() {
        while (true) {
            BlockGroup blockGroup = findBlockGroup();
            if (blockGroup == null) {
                return;
            }
            removeBlock(blockGroup);
            gravity();
            rotate();
            gravity();
        }
    }

    private static BlockGroup findBlockGroup() {
        BlockGroup maxBlockGroup = new BlockGroup(new Point(0, 0), 0, 0);
        visited = new boolean[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] > RAINBOW) {
                    resetVisitedRainbow();
                    BlockGroup blockGroup = searchMaxBlockGroup(new Point(x, y), map[x][y]);
                    if (blockGroup == null) continue;
                    if (maxBlockGroup.compareTo(blockGroup) > 0) {
                        maxBlockGroup = blockGroup;
                    }
                }
            }
        }

        if (maxBlockGroup.size == 0) {
            return null;
        }
        return maxBlockGroup;
    }

    private static void resetVisitedRainbow() {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (map[x][y] == RAINBOW) {
                    visited[x][y] = false;
                }
            }
        }
    }

    private static BlockGroup searchMaxBlockGroup(Point point, int color) {
        int size = 1;
        int rainbow = 0;
        Queue<Point> points = new LinkedList<>();
        points.add(point);
        point.setVisit();
        while (!points.isEmpty()) {
            Point cur = points.poll();
            for (int dir = 0; dir < 4; dir++) {
                Point nextPoint = cur.nextPoint(dir);
                if (nextPoint.isInRange() && !nextPoint.isVisit() && nextPoint.isAdjoin(color)) {
                    nextPoint.setVisit();
                    points.add(nextPoint);
                    if (nextPoint.getColor() == RAINBOW) {
                        rainbow++;
                    }
                    size++;
                }
            }
        }
        if (size < 2) {
            return null;
        }
        return new BlockGroup(point, rainbow, size);
    }

    private static void removeBlock(BlockGroup block) {
        visited = new boolean[N][N];
        if (block.size > 1) {
            answer += block.size * block.size;
            Queue<Point> points = new LinkedList<>();
            points.add(block.point);
            int color = block.point.getColor();
            while (!points.isEmpty()) {
                Point point = points.poll();
                point.setBlank();
                for (int dir = 0; dir < 4; dir++) {
                    Point nextPoint = point.nextPoint(dir);
                    if (nextPoint.isInRange() && !nextPoint.isVisit() && nextPoint.isAdjoin(color)) {
                        nextPoint.setVisit();
                        points.add(nextPoint);
                    }
                }
            }
        }
    }

    private static void gravity() {
        for (int y = 0; y < N; y++) {
            for (int x = N - 1; x > 0; x--) {
                int nx = x;
                while (nx > 0 && map[nx--][y] == EMPTY) {
                    if (map[nx][y] >= RAINBOW) {
                        int temp = map[x][y];
                        map[x][y] = map[nx][y];
                        map[nx][y] = temp;
                        break;
                    }
                }
            }
        }
    }

    private static void rotate() {
        int[][] rotate = new int[N][N];
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                rotate[x][y] = map[y][N - 1 - x];
            }
        }
        map = rotate;
    }

    static class BlockGroup implements Comparable<BlockGroup> {
        Point point;
        int rainbowCount;
        int size;

        public BlockGroup(Point point, int rainbowCount, int size) {
            this.point = point;
            this.rainbowCount = rainbowCount;
            this.size = size;
        }

        @Override
        public int compareTo(BlockGroup o) {
            if (this.size == o.size) {
                if (this.rainbowCount == o.rainbowCount) {
                    if (this.point.x == o.point.x) {
                        return o.point.y - this.point.y;
                    }
                    return o.point.x - this.point.x;
                }
                return o.rainbowCount - this.rainbowCount;
            }
            return o.size - this.size;
        }
    }

    static class Point {
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isInRange() {
            return x >= 0 && y >= 0 && x < N && y < N;
        }

        public boolean isAdjoin(int color) {
            return getColor() == color || getColor() == RAINBOW;
        }

        public boolean isVisit() {
            return visited[x][y];
        }

        public void setVisit() {
            visited[x][y] = true;
        }

        public void setBlank() {
            map[x][y] = EMPTY;
        }

        public Point nextPoint(int dir) {
            return new Point(x + dx[dir], y + dy[dir]);
        }

        public int getColor() {
            return map[x][y];
        }
    }
}