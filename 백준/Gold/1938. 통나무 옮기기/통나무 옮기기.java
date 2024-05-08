import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static char[][] area;
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
    static boolean[][][] visited;
    private static int N;
    static final int MAX_WOOD = 3;
    static final int HORIZONTAL = 0;
    static final int VERTICAL = 1;
    static final int U = 0, D = 1, L = 2, R = 3, T = 4;
    static List<Point> end;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        area = new char[N][N];
        Wood initWood = new Wood();
        end = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            area[i] = br.readLine().toCharArray();

            for (int j = 0; j < N; j++) {
                if (area[i][j] == 'B') {
                    initWood.addPoint(new Point(i, j));
                    area[i][j] = '0';
                } else if (area[i][j] == 'E') {
                    end.add(new Point(i, j));
                    area[i][j] = '0';
                }
            }
        }

        visited = new boolean[2][N][N];
        Queue<Wood> woods = new ArrayDeque<>();
        for (Point point : initWood.points) {
            visited[initWood.rotation][point.x][point.y] = true;
        }

        woods.add(initWood);
        int ans = 0;
        while (!woods.isEmpty()) {
            Wood wood = woods.poll();
            if (Objects.equals(wood.points, end)) {
                ans = wood.move;
                break;
            }

            for (int i = 0; i < 4; i++) {
                if (wood.canMove(i)) {
                    woods.add(new Wood(wood.move(i), wood.move + 1, wood.rotation));
                }
            }

            if (wood.canRotate()) {
                woods.add(new Wood(wood.rotate(), wood.move + 1, wood.rotation ^ 1));
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Wood {

        List<Point> points;
        int move;
        int rotation;

        public Wood() {
            points = new ArrayList<>();
            move = 0;
            rotation = 1;
        }

        public Wood(List<Point> points, int move, int rotation) {
            this.points = points;
            this.move = move;
            this.rotation = rotation;
        }

        public boolean canMove(int dir) {
            if (rotation == HORIZONTAL) {
                if (dir == D || dir == U) {
                    for (Point point : points) {
                        if (!point.canNext(dir, rotation)) {
                            return false;
                        }
                    }
                } else if (dir == L) {
                    return points.get(0).canNext(dir, rotation);
                } else if (dir == R) {
                    return points.get(MAX_WOOD - 1).canNext(dir, rotation);
                }
            } else if (rotation == VERTICAL) {
                if (dir == U) {
                    return points.get(0).canNext(dir, rotation);
                } else if (dir == D) {
                    return points.get(MAX_WOOD - 1).canNext(dir, rotation);
                } else if (dir == L || dir == R) {
                    for (Point point : points) {
                        if (!point.canNext(dir, rotation)) {
                            return false;
                        }
                    }
                }
            }
            return true;
        }

        public boolean canRotate() {
            if (rotation == VERTICAL) {
                for (Point point : points) {
                    if (!point.canRotate(L, VERTICAL)) {
                        return false;
                    }

                    if (!point.canRotate(R, VERTICAL)) {
                        return false;
                    }
                }
            } else if (rotation == HORIZONTAL) {
                for (Point point : points) {
                    if (!point.canRotate(U, HORIZONTAL)) {
                        return false;
                    }

                    if (!point.canRotate(D, HORIZONTAL)) {
                        return false;
                    }
                }
            }
            return true;
        }

        public void addPoint(Point point) {
            if (points.size() == MAX_WOOD - 1) {
                Point p2 = points.get(points.size() - 1);
                if (p2.x - point.x == 0) {
                    rotation = HORIZONTAL;
                } else {
                    rotation = VERTICAL;
                }
            }

            points.add(point);
        }

        public List<Point> move(int dir) {
            List<Point> tmp = new ArrayList<>();
            for (Point point : points) {
                Point next = point.next(dir);
                tmp.add(next);
                visited[rotation][next.x][next.y] = true;
            }

            return tmp;
        }

        public List<Point> rotate() {

            List<Point> tmp = new ArrayList<>();
            Point e, e1, e2;
            e = e1 = e2 = null;
            if (rotation == HORIZONTAL) {
                e = new Point(points.get(0).x - 1, points.get(0).y + 1);
                e1 = new Point(points.get(1).x, points.get(1).y);
                e2 = new Point(points.get(2).x + 1, points.get(2).y - 1);
                visited[rotation ^ 1][e.x][e.y] = true;
                visited[rotation ^ 1][e1.x][e1.y] = true;
                visited[rotation ^ 1][e2.x][e2.y] = true;
            } else if (rotation == VERTICAL) {
                e = new Point(points.get(0).x + 1, points.get(0).y - 1);
                e1 = new Point(points.get(1).x, points.get(1).y);
                e2 = new Point(points.get(2).x - 1, points.get(2).y + 1);
            }
            visited[rotation ^ 1][e.x][e.y] = true;
            visited[rotation ^ 1][e1.x][e1.y] = true;
            visited[rotation ^ 1][e2.x][e2.y] = true;
            tmp.add(e);
            tmp.add(e1);
            tmp.add(e2);
            return tmp;
        }
    }

    static class Point {

        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public Point next(int dir) {
            return new Point(x + dx[dir], y + dy[dir]);
        }

        public boolean canNext(int dir, int rotation) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            return inRange(nx, ny) && !visited[rotation][nx][ny] && area[nx][ny] == '0';
        }

        public boolean canRotate(int dir, int rotation) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            return inRange(nx, ny) && !visited[rotation ^ 1][nx][ny] && area[nx][ny] == '0';
        }

        static boolean inRange(int x, int y) {
            return x >= 0 && y >= 0 && x < N && y < N;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Point point = (Point) o;
            return x == point.x && y == point.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}