import java.io.*;
import java.util.*;

class Shark {
    int x, y, move;

    public Shark(int x, int y, int move) {
        this.x = x;
        this.y = y;
        this.move = move;
    }
}

public class Main {
    static int N;
    static int sharkX, sharkY;
    static int sharkSize = 2;
    static int[][] board;
    static int[] dx = new int[]{-1, 0, 0, 1};
    static int[] dy = new int[]{0, -1, 1, 0};
    static ArrayList<int[]> points;
    static int eatingCount = 0;
    static int time = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer;
        points = new ArrayList<>();

        N = Integer.parseInt(br.readLine());
        board = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                board[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (board[i][j] == 9) {
                    board[i][j] = 0;
                    sharkX = i;
                    sharkY = j;
                }
            }
        }

        solution();
        bw.write(time + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        while (true) {
            if (eatingCount == sharkSize) {
                sharkSize++;
                eatingCount = 0;
            }
            findFeeds(sharkX, sharkY);
            if (points.isEmpty()) {
                break;
            }
            points.sort((o1, o2) -> o1[2] == o2[2] ? (o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]) : o1[2] - o2[2]);
            int[] targetInfo = points.get(0);
            board[targetInfo[0]][targetInfo[1]] = 0;
            eatingCount++;
            sharkX = targetInfo[0];
            sharkY = targetInfo[1];
            time += targetInfo[2];
            points.clear();
        }
    }

    public static void findFeeds(int sharkX, int sharkY) {
        Queue<Shark> queue = new LinkedList<>();
        queue.add(new Shark(sharkX, sharkY, 0));
        boolean[][] visited = new boolean[N + 1][N + 1];
        while (!queue.isEmpty()) {
            Shark shark = queue.poll();
            int x = shark.x;
            int y = shark.y;
            int move = shark.move;
            if (canEat(board[x][y], sharkSize)) {
                points.add(new int[]{x, y, move});
            }
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (isInRange(nx, ny) && !visited[nx][ny] && canMove(board[nx][ny], sharkSize)) {
                    visited[nx][ny] = true;
                    queue.add(new Shark(nx, ny, move + 1));
                }
            }
        }
    }

    private static boolean canEat(int feedSize, int sharkSize) {
        return feedSize < sharkSize && feedSize > 0;
    }

    private static boolean canMove(int feedSize, int sharkSize) {
        return feedSize <= sharkSize;
    }

    public static boolean isInRange(int x, int y) {
        return x > 0 && y > 0 && x <= N && y <= N;
    }
}