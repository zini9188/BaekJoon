import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static final int EAST = 1, WEST = 2, NORTH = 3, SOUTH = 4;
    static final int UP = 1, BACK = 2, RIGHT = 3, LEFT = 4, FRONT = 5, DOWN = 6;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static int[][] map;
    static int[] dice;
    static int[] dx = {0, 0, 0, -1, 1};
    static int[] dy = {0, 1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        int x = Integer.parseInt(tokenizer.nextToken());
        int y = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        dice = new int[7];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int dir = Integer.parseInt(tokenizer.nextToken());
            if (isInRange(x + dx[dir], y + dy[dir])) {
                x += dx[dir];
                y += dy[dir];
                move(dir, x, y);
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    private static void move(int dir, int x, int y) {
        int up = dice[UP];

        if (dir == SOUTH) {
            dice[UP] = dice[BACK];
            dice[BACK] = dice[DOWN];
            dice[DOWN] = dice[FRONT];
            dice[FRONT] = up;
        }

        if (dir == NORTH) {
            dice[UP] = dice[FRONT];
            dice[FRONT] = dice[DOWN];
            dice[DOWN] = dice[BACK];
            dice[BACK] = up;
        }

        if (dir == WEST) {
            dice[UP] = dice[RIGHT];
            dice[RIGHT] = dice[DOWN];
            dice[DOWN] = dice[LEFT];
            dice[LEFT] = up;
        }

        if (dir == EAST) {
            dice[UP] = dice[LEFT];
            dice[LEFT] = dice[DOWN];
            dice[DOWN] = dice[RIGHT];
            dice[RIGHT] = up;
        }

        if (map[x][y] == 0) {
            map[x][y] = dice[DOWN];
        } else {
            dice[DOWN] = map[x][y];
            map[x][y] = 0;
        }

        sb.append(dice[UP]).append("\n");
    }
}