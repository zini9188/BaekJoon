import java.io.*;
import java.util.*;

public class Main {

    static final char EXIT = 'B';
    static final char WALL = '.';
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int M, N;
    static CrossRoad[] crossRoads;
    static char[][] map;
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    static Point s;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        while (true) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            if (M == 0 && N == 0) {
                break;
            }
            map = new char[M][N];
            visited = new boolean[M][N];
            int crossRoadCount = 0;
            for (int i = 0; i < M; i++) {
                map[i] = br.readLine().toCharArray();
                for (int j = 0; j < N; j++) {
                    if (map[i][j] == 'A') {
                        s = new Point(i, j, 0);
                    }

                    if (Character.isDigit(map[i][j])) {
                        crossRoadCount++;
                    }
                }
            }

            crossRoads = new CrossRoad[crossRoadCount];
            for (int i = 0; i < crossRoadCount; i++) {
                String[] input = br.readLine().split(" ");

                if (input[1].equals("-")) {
                    crossRoads[i] = new CrossRoad(input[1].charAt(0), Integer.parseInt(input[2]),
                            Integer.parseInt(input[3]));
                } else {
                    crossRoads[i] = new CrossRoad(input[1].charAt(0), Integer.parseInt(input[3]),
                            Integer.parseInt(input[2]));
                }
            }

            int ans = bfs();
            sb.append(ans == -1 ? "impossible" : ans).append("\n");
            br.readLine();
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs() {
        Queue<Point> q = new ArrayDeque<>();
        q.add(new Point(s.x, s.y, 0));
        visited[s.x][s.y] = true;
        while (!q.isEmpty()) {
            Point car = q.poll();

            if (map[car.x][car.y] == EXIT) {
                return car.t;
            }

            for (int j = 0; j < 4; j++) {
                int nx = car.x + dx[j];
                int ny = car.y + dy[j];
                int nt = car.t + 1;

                if (outOfRange(nx, ny) || map[nx][ny] == WALL || visited[nx][ny]) {
                    continue;
                }

                if (Character.isDigit(map[nx][ny])) {
                    CrossRoad crossRoad = crossRoads[map[nx][ny] - '0'];
                    int time = nt % (crossRoad.a + crossRoad.b);

                    if (time == 0 || time > crossRoad.a) {
                        if (crossRoad.direction == '|') {
                            if (j >= 2) {
                                visited[nx][ny] = true;
                                q.add(new Point(nx, ny, nt));
                            } else {
                                q.add(new Point(car.x, car.y, nt));
                            }
                        } else {
                            if (j >= 2) {
                                q.add(new Point(car.x, car.y, nt));
                            } else {
                                visited[nx][ny] = true;
                                q.add(new Point(nx, ny, nt));
                            }
                        }
                    } else {
                        if (crossRoad.direction == '-') {
                            if (j >= 2) {
                                visited[nx][ny] = true;
                                q.add(new Point(nx, ny, nt));
                            } else {
                                q.add(new Point(car.x, car.y, nt));
                            }
                        } else {
                            if (j >= 2) {
                                q.add(new Point(car.x, car.y, nt));
                            } else {
                                visited[nx][ny] = true;
                                q.add(new Point(nx, ny, nt));
                            }
                        }
                    }
                } else {
                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, nt));
                }
            }
        }
        
        return -1;
    }

    static boolean outOfRange(int x, int y) {
        return x < 0 || y < 0 || x >= M || y >= N;
    }

    static class Point {

        int x, y, t;

        public Point(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    static class CrossRoad {

        char direction;
        int a, b;

        public CrossRoad(char direction, int a, int b) {
            this.direction = direction;
            this.a = a;
            this.b = b;
        }
    }
}