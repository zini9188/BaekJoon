import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N;
    static int[][] board;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int answer = 0;
    static SandRate[][] sandRates = {
            // 서
            {
                    new SandRate(-1, 0, 7),
                    new SandRate(1, 0, 7),
                    new SandRate(-1, 1, 1),
                    new SandRate(1, 1, 1),
                    new SandRate(-1, -1, 10),
                    new SandRate(1, -1, 10),
                    new SandRate(-2, 0, 2),
                    new SandRate(2, 0, 2),
                    new SandRate(0, -2, 5),
            },
            // 남
            {
                    new SandRate(0, -1, 7),
                    new SandRate(0, 1, 7),
                    new SandRate(-1, -1, 1),
                    new SandRate(-1, 1, 1),
                    new SandRate(1, -1, 10),
                    new SandRate(1, 1, 10),
                    new SandRate(0, -2, 2),
                    new SandRate(0, 2, 2),
                    new SandRate(2, 0, 5),
            },
            // 동
            {
                    new SandRate(1, 0, 7),
                    new SandRate(-1, 0, 7),
                    new SandRate(-1, -1, 1),
                    new SandRate(1, -1, 1),
                    new SandRate(-1, 1, 10),
                    new SandRate(1, 1, 10),
                    new SandRate(-2, 0, 2),
                    new SandRate(2, 0, 2),
                    new SandRate(0, 2, 5),
            },
            // 북
            {
                    new SandRate(0, -1, 7),
                    new SandRate(0, 1, 7),
                    new SandRate(1, -1, 1),
                    new SandRate(1, 1, 1),
                    new SandRate(-1, -1, 10),
                    new SandRate(-1, 1, 10),
                    new SandRate(0, -2, 2),
                    new SandRate(0, 2, 2),
                    new SandRate(-2, 0, 5)
            }
    };

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        
        board = new int[N + 1][N + 1];
        for (int x = 1; x <= N; x++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            for (int y = 1; y <= N; y++) {
                board[x][y] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        Tornado tornado = new Tornado(N / 2 + 1, N / 2 + 1, 0);
        int count = 1;
        while (!tornado.isArrival()) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < count; j++) {
                    if (tornado.isArrival()) {
                        break;
                    }               
                    
                    tornado.move();
                    
                    if (board[tornado.x][tornado.y] > 0) {
                        spreadSand(tornado);
                    }
                }
                tornado.dir = (tornado.dir + 1) % 4;
            }
            count++;
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void spreadSand(Tornado tornado) {
        int x = tornado.x;
        int y = tornado.y;
        int totalOutSpreadSand = 0;
        int totalSpreadSand = 0;
        for (int i = 0; i < 9; i++) {
            int nx = x + sandRates[tornado.dir][i].x;
            int ny = y + sandRates[tornado.dir][i].y;
            int spread = (board[x][y] * sandRates[tornado.dir][i].rate) / 100;
            totalSpreadSand += spread;
            if (isInRange(nx, ny)) {
                board[nx][ny] += spread;
            } else {
                totalOutSpreadSand += spread;
            }
        }
        
        int nx = x + dx[tornado.dir];
        int ny = y + dy[tornado.dir];
        if (isInRange(nx, ny)) {
            board[nx][ny] += board[x][y] - totalSpreadSand;
        } else {
            answer += board[x][y] - totalSpreadSand;
        }
        answer += totalOutSpreadSand;
        board[x][y] = 0;
    }

    static boolean isInRange(int x, int y) {
        return x >= 1 && y >= 1 && x <= N && y <= N;
    }

    static class Tornado {
        int x;
        int y;
        int dir;

        public Tornado(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void move() {
            this.x += dx[dir];
            this.y += dy[dir];
        }

        public boolean isArrival() {
            return x == 1 && y == 1;
        }
    }

    static class SandRate {
        int x, y, rate;

        public SandRate(int x, int y, int rate) {
            this.x = x;
            this.y = y;
            this.rate = rate;
        }
    }
}