import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int R, C;
    static char[][] map;
    static Queue<Point> hedgehog = new LinkedList<>();
    static Queue<Point> water = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        map = new char[R][C];

        for (int x = 0; x < R; x++) {
            String line = br.readLine();
            for (int y = 0; y < C; y++) {
                map[x][y] = line.charAt(y);
                if (map[x][y] == 'S') {
                    hedgehog.add(new Point(x, y));
                }
                if (map[x][y] == '*') {
                    water.add(new Point(x, y));
                }
            }
        }

        int answer = bfs();

        bw.write(answer == -1 ? "KAKTUS" : answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int bfs() {
        int time = 0;
        while (!hedgehog.isEmpty()) {
            int len = water.size();
            for (int i = 0; i < len && !water.isEmpty(); i++) {
                Point current = water.poll();
                for (int dir = 0; dir < 4; dir++) {
                    Point next = current.move(dir);
                    if (isInRange(next) && (isEmpty(next) || isHedgehog(next))) {
                        map[next.x][next.y] = '*';
                        water.add(next);
                    }
                }
            }

            len = hedgehog.size();
            for (int i = 0; i < len && !hedgehog.isEmpty(); i++) {
                Point current = hedgehog.poll();
                for (int dir = 0; dir < 4; dir++) {
                    Point next = current.move(dir);
                    if (isInRange(next)) {
                        if (map[next.x][next.y] == 'D') {
                            return time + 1;
                        }
                        if (isEmpty(next)) {
                            hedgehog.add(next);
                            map[next.x][next.y] = 'S';
                        }
                    }
                }
            }
            time++;
        }
        return -1;
    }

    private static boolean isHedgehog(Point next) {
        return map[next.x][next.y] == 'S';
    }

    private static boolean isEmpty(Point point) {
        return map[point.x][point.y] == '.';
    }

    private static boolean isInRange(Point point) {
        return point.x >= 0 && point.x < R && point.y >= 0 && point.y < C;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point move(int dir) {
            return new Point(x + dx[dir], y + dy[dir]);
        }
    }
}