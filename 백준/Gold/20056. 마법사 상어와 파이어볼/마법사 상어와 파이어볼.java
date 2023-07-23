import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, K;
    static FireBall[][] board;
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        board = new FireBall[N][N];
        Queue<FireBall> fireBalls = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(tokenizer.nextToken()) - 1;
            int y = Integer.parseInt(tokenizer.nextToken()) - 1;
            int weight = Integer.parseInt(tokenizer.nextToken());
            int speed = Integer.parseInt(tokenizer.nextToken());
            int direction = Integer.parseInt(tokenizer.nextToken());
            fireBalls.add(new FireBall(x, y, weight, speed, direction, 1, true));
        }

        for (int k = 0; k < K; k++) {
            moveFireBalls(fireBalls);
            mergeFireBalls(fireBalls);
        }

        int answer = 0;
        while (!fireBalls.isEmpty()) {
            answer += fireBalls.poll().weight;
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void mergeFireBalls(Queue<FireBall> fireBalls) {
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                if (board[x][y] != null) {
                    if (board[x][y].count >= 2) {
                        int weight = board[x][y].weight / 5;
                        int speed = board[x][y].speed / board[x][y].count;
                        if (weight == 0) {
                            board[x][y] = null;
                            continue;
                        }
                        int maxDir = board[x][y].allSame ? 6 : 7;
                        int startDir = board[x][y].allSame ? 0 : 1;
                        for (int dir = startDir; dir <= maxDir; dir += 2) {
                            fireBalls.add(new FireBall(x, y, weight, speed, dir, 1, true));
                        }
                    } else {
                        fireBalls.add(board[x][y]);
                    }
                    board[x][y] = null;
                }
            }
        }
    }

    private static void moveFireBalls(Queue<FireBall> fireBalls) {
        int size = fireBalls.size();
        for (int i = 0; i < size; i++) {
            FireBall fireBall = fireBalls.poll();
            fireBall.move();
            int cx = fireBall.x;
            int cy = fireBall.y;
            if (board[cx][cy] == null) {
                board[cx][cy] = fireBall;
            } else {
                board[cx][cy].count++;
                board[cx][cy].weight += fireBall.weight;
                board[cx][cy].speed += fireBall.speed;
                if (board[cx][cy].direction % 2 != fireBall.direction % 2) {
                    board[cx][cy].allSame = false;
                }
            }
        }
    }

    static class FireBall {
        int x, y;
        int weight;
        int speed;
        int direction;
        int count;
        boolean allSame;

        public FireBall(int x, int y, int weight, int speed, int direction, int count, boolean allSame) {
            this.x = x;
            this.y = y;
            this.weight = weight;
            this.speed = speed;
            this.direction = direction;
            this.count = count;
            this.allSame = allSame;
        }

        public void move() {
            x = (x + N + dx[direction] * (speed % N)) % N;
            y = (y + N + dy[direction] * (speed % N)) % N;
        }
    }
}