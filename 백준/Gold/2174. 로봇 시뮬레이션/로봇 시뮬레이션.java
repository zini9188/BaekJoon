import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int A, B;
    static int N, M;
    static int[][] ground;
    static String[] directions = {"N", "E", "S", "W"};
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static final int CRASH_WITH_WALL = -2;
    static final int CRASH_WITH_ROBOT = 1;
    static final int CLEAR = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        A = Integer.parseInt(tokenizer.nextToken());
        B = Integer.parseInt(tokenizer.nextToken());
        ground = new int[B + 1][A + 1];

        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        Robot[] robots = new Robot[N + 1];
        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(tokenizer.nextToken());
            int x = Integer.parseInt(tokenizer.nextToken());
            int dir = getDir(tokenizer.nextToken());
            robots[i] = new Robot(x, y, dir);
            ground[x][y] = i;
        }

        Queue<Command> commands = new LinkedList<>();
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(tokenizer.nextToken());
            String cmd = tokenizer.nextToken();
            int count = Integer.parseInt(tokenizer.nextToken());
            commands.add(new Command(num, cmd, count));
        }

        bw.write(simulation(robots, commands));
        bw.flush();
        bw.close();
        br.close();
    }

    private static String simulation(Robot[] robots, Queue<Command> commands) {
        while (!commands.isEmpty()) {
            Command command = commands.poll();
            int robotNumber = command.number;
            int count = command.count;
            String cmd = command.cmd;
            
            Robot robot = robots[robotNumber];
            for (int i = 0; i < count; i++) {
                if (cmd.equals("F")) {
                    int state = robot.move();
                    if (state == CRASH_WITH_WALL) {
                        return "Robot " + robotNumber + " crashes into the wall";
                    } else if (state >= CRASH_WITH_ROBOT) {
                        return "Robot " + robotNumber + " crashes into robot " + state;
                    }
                } else {
                    robot.rotate(cmd);
                }
            }
        }
        return "OK";
    }

    static int getDir(String dir) {
        for (int i = 0; i < directions.length; i++) {
            if (dir.equals(directions[i])) {
                return i;
            }
        }
        return -1;
    }

    static class Robot {
        int x, y;
        int dir;

        public Robot(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }

        public void rotate(String cmd) {
            if (cmd.equals("L")) {
                dir = (dir + 3) % 4;
            } else if (cmd.equals("R")) {
                dir = (dir + 1) % 4;
            }
        }

        public int move() {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (isInRange(nx, ny)) {
                if (ground[nx][ny] >= 1) {
                    return ground[nx][ny];
                } else {
                    ground[nx][ny] = ground[x][y];
                    ground[x][y] = 0;
                    this.x = nx;
                    this.y = ny;
                    return CLEAR;
                }
            }
            return CRASH_WITH_WALL;
        }

        public boolean isInRange(int x, int y) {
            return x >= 1 && y >= 1 && x <= B && y <= A;
        }
    }

    static class Command {
        int number;
        String cmd;
        int count;

        public Command(int number, String cmd, int count) {
            this.number = number;
            this.cmd = cmd;
            this.count = count;
        }
    }
}
