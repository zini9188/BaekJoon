import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] map;
    static int N, M, H;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        H = Integer.parseInt(tokenizer.nextToken());

        map = new int[H + 1][N + 1];
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            map[a][b] = b + 1;
            map[a][b + 1] = b;
        }

        bw.write(String.valueOf(solution()));
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution() {
        for (int rowLine = 0; rowLine < 4; rowLine++) {
            if (setLadder(1, 1, 0, rowLine)) {
                return rowLine;
            }
        }
        return -1;
    }

    private static boolean setLadder(int x, int y, int count, int max) {
        if (count == max) {
            return isAllLaddersArrive();
        }

        for (int nx = x; nx <= H; nx++) {
            for (int ny = y; ny < N; ny++) {
                if (map[nx][ny] == 0 && map[nx][ny + 1] == 0) {
                    map[nx][ny] = ny + 1;
                    map[nx][ny + 1] = ny;
                    if (setLadder(nx, 1, count + 1, max)) {
                        return true;
                    }
                    map[nx][ny] = 0;
                    map[nx][ny + 1] = 0;
                }
            }
        }
        return false;
    }

    private static boolean isAllLaddersArrive() {
        int count = 0;
        for (int i = 1; i <= N; i++) {
            if (isLadderArrive(i)) {
                count++;
            }
        }
        return count == N;
    }

    private static boolean isLadderArrive(int start) {
        int x = 1;
        int y = start;
        while (x <= H) {
            if (map[x][y] > 0) {
                y = map[x][y];
            }
            x++;
        }
        return start == y;
    }
}
