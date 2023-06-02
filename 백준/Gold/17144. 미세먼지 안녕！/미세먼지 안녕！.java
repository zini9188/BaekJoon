import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static final int[] dx = {0, 0, 1, -1};
    private static final int[] dy = {1, -1, 0, 0};
    static Queue<Dust> queue;
    private static int R;
    private static int C;
    private static int T;
    private static int[][] map;
    private static ArrayList<Integer> machines;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        T = Integer.parseInt(tokenizer.nextToken());
        map = new int[R][C];
        queue = new LinkedList<>();
        machines = new ArrayList<>();

        for (int i = 0; i < R; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (map[i][j] == -1) {
                    machines.add(i);
                }
            }
        }
        
        solution();
        
        bw.write(getDustAmount() + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int getDustAmount() {
        int answer = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0)
                    answer += map[i][j];
            }
        }
        return answer;
    }

    private static void solution() {
        for (int i = 0; i < T; i++) {
            addDust();
            spread();
            airClean();
        }
    }

    private static void airClean() {
        int up = machines.get(0);
        int down = machines.get(1);

        for (int x = up - 1; x > 0; x--) {
            map[x][0] = map[x - 1][0];
        }
        for (int y = 0; y < C - 1; y++) {
            map[0][y] = map[0][y + 1];
        }
        for (int x = 0; x < up; x++) {
            map[x][C - 1] = map[x + 1][C - 1];
        }
        for (int y = C - 1; y > 1; y--) {
            map[up][y] = map[up][y - 1];
        }
        map[up][1] = 0;

        for (int x = down + 1; x < R - 1; x++) {
            map[x][0] = map[x + 1][0];
        }
        for (int y = 0; y < C - 1; y++) {
            map[R - 1][y] = map[R - 1][y + 1];
        }
        for (int x = R - 1; x > down; x--) {
            map[x][C - 1] = map[x - 1][C - 1];
        }
        for (int y = C - 1; y > 1; y--) {
            map[down][y] = map[down][y - 1];
        }
        map[down][1] = 0;
    }

    private static void addDust() {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] > 0)
                    queue.add(new Dust(i, j, map[i][j]));
            }
        }
    }

    private static void spread() {
        while (!queue.isEmpty()) {
            Dust dust = queue.poll();
            int x = dust.x;
            int y = dust.y;
            int amount = dust.amount / 5;
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                int total = 0;
                if (isInRange(nx, ny) && map[nx][ny] != -1) {
                    map[nx][ny] += amount;
                    total += amount;
                }
                map[x][y] -= total;
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < R && y < C;

    }

    static class Dust {
        int x, y, amount;

        public Dust(int x, int y, int amount) {
            this.x = x;
            this.y = y;
            this.amount = amount;
        }
    }
}