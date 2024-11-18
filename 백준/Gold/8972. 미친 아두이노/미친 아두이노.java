import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] dx = {0, 1, 1, 1, 0, 0, 0, -1, -1, -1}, dy = {0, -1, 0, 1, -1, 0, 1, -1, 0,
            1};
    private static List<Integer>[][] q;
    private static int R, C;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        char[][] map = new char[R][C];
        q = new List[R][C];
        Queue<Point> temp = new ArrayDeque<>();
        Point arduino = new Point(0, 0);
        for (int i = 0; i < R; i++) {
            map[i] = br.readLine().toCharArray();

            for (int j = 0; j < C; j++) {
                q[i][j] = new LinkedList<>();
                if (map[i][j] == 'I') {
                    arduino = new Point(i, j);
                } else if (map[i][j] == 'R') {
                    temp.add(new Point(i, j));
                }
                map[i][j] = '.';
            }
        }

        int size = temp.size();
        Point[] crazyArduino = new Point[size + 1];
        for (int i = 1; i <= size; i++) {
            crazyArduino[i] = temp.poll();
        }

        boolean[] terminated = new boolean[size + 1];
        char[] command = br.readLine().toCharArray();
        for (int turn = 0; turn < command.length; turn++) {
            int dir = command[turn] - '0';

            arduino.move(dir);

            if (arduino.isThereCrazyArduino(turn + 1)) {
                break;
            }

            for (int i = 1; i <= size; i++) {
                if (terminated[i]) {
                    continue;
                }

                int nd = -1;
                int minDist = Integer.MAX_VALUE;
                int x = crazyArduino[i].x;
                int y = crazyArduino[i].y;
                for (int i1 = 1; i1 <= 9; i1++) {
                    if (i1 == 5) {
                        continue;
                    }

                    int nx = x + dx[i1];
                    int ny = y + dy[i1];
                    if (outRange(nx, ny)) {
                        continue;
                    }

                    int dist = getDistance(arduino.x, arduino.y, nx, ny);
                    if (minDist >= dist) {
                        minDist = dist;
                        nd = i1;
                    }
                }

                crazyArduino[i].remove(i);
                crazyArduino[i].move(nd);
                crazyArduino[i].getList().add(i);
            }

            if (arduino.isThereCrazyArduino(turn + 1)) {
                break;
            }

            for (int i = 1; i <= size; i++) {
                if (terminated[i]) {
                    continue;
                }

                if (crazyArduino[i].getList().size() >= 2) {
                    for (Integer index : crazyArduino[i].getList()) {
                        terminated[index] = true;
                    }
                    crazyArduino[i].getList().clear();
                }
            }
        }

        if (sb.toString().isEmpty()) {
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    if (i == arduino.x && j == arduino.y) {
                        sb.append("I");
                    } else if (q[i][j].isEmpty()) {
                        sb.append(".");
                    } else {
                        sb.append("R");
                    }
                }
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static int getDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= R || y >= C;
    }

    static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(int dir) {
            x += dx[dir];
            y += dy[dir];
        }

        public boolean isThereCrazyArduino(int idx) {
            if (q[x][y].isEmpty()) {
                return false;
            }

            sb.append("kraj ").append(idx);
            return true;
        }

        public List<Integer> getList() {
            return q[x][y];
        }

        public void remove(int idx) {
            q[x][y].remove((Object) idx);
        }
    }
}