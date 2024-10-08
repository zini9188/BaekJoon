import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    private static int N, K;
    private static int[][] color, horse;
    private static int[] dx = {0, 1, 0, -1}, dy = {1, 0, -1, 0};
    private static LinkedList<Node>[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        color = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                color[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        horse = new int[K][2];
        map = new LinkedList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new LinkedList<>();
            }
        }

        int a, b, c;
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken()) - 1;
            b = Integer.parseInt(st.nextToken()) - 1;
            c = Integer.parseInt(st.nextToken());

            if (c == 1) {
                c = 0;
            } else if (c == 4) {
                c = 1;
            }

            horse[i][0] = a;
            horse[i][1] = b;

            map[a][b].add(new Node(i, c));
        }
        game();
    }

    private static void game() {
        for (int t = 1; t <= 1000; t++) {
            for (int k = 0; k < K; k++) {
                int x = horse[k][0];
                int y = horse[k][1];
                int d = map[x][y].get(0).d;

                if (map[x][y].get(0).n != k) {
                    continue;
                }

                int nx = x + dx[d];
                int ny = y + dy[d];
                if (outRange(nx, ny) || color[nx][ny] == 2) {
                    map[x][y].get(0).d = d = (d + 2) % 4;
                    nx = x + dx[d];
                    ny = y + dy[d];

                    if (!(outRange(nx, ny) || color[nx][ny] == 2)) {
                        if (move(x, y, nx, ny, color[nx][ny])) {
                            System.out.println(t);
                            return;
                        }
                    }
                } else {
                    if (move(x, y, nx, ny, color[nx][ny])) {
                        System.out.println(t);
                        return;
                    }
                }
            }
        }
        System.out.println("-1");
    }

    private static boolean outRange(int n, int y) {
        return n < 0 || n >= N || y < 0 || y >= N;
    }

    private static boolean move(int px, int py, int nx, int ny, int color) {
        if (color == 0) {
            while (map[px][py].size() > 0) {
                Node temp = map[px][py].removeFirst();
                horse[temp.n][0] = nx;
                horse[temp.n][1] = ny;
                map[nx][ny].add(temp);
            }
        } else {
            while (map[px][py].size() > 0) {
                Node temp = map[px][py].removeLast();
                horse[temp.n][0] = nx;
                horse[temp.n][1] = ny;
                map[nx][ny].add(temp);
            }
        }
        return map[nx][ny].size() >= 4;
    }

    private static class Node {

        int n, d;

        Node(int n, int d) {
            this.n = n;
            this.d = d;
        }
    }
}