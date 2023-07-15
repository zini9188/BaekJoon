import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int A, B;
    static int N, M;
    static int[][] map;
    static String[] directions = {"N", "E", "S", "W"};
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        A = Integer.parseInt(tokenizer.nextToken());
        B = Integer.parseInt(tokenizer.nextToken());
        map = new int[B + 1][A + 1];

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
            map[x][y] = i;
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
            Command current = commands.poll();
            int curRobot = current.number;
            int count = current.count;
            String cmd = current.cmd;

            Robot robot = robots[curRobot];
            for (int i = 0; i < count; i++) {
                if (cmd.equals("F")) {
                    int state = robot.move();
                    if (state == -2) {
                        return "Robot " + curRobot + " crashes into the wall";
                    } else if (state >= 1) {
                        return "Robot " + curRobot + " crashes into robot " + state;
                    }
                } else {
                    robot.operate(cmd);
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

        public void operate(String cmd) {
            if (cmd.equals("L")) {
                if (dir == 0) {
                    dir = 3;
                } else {
                    dir--;
                }
            } else if (cmd.equals("R")) {
                dir++;
                dir %= 4;
            }
        }


        public boolean isInRange(int x, int y) {
            return x >= 1 && y >= 1 && x <= B && y <= A;
        }

        public int move() {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            if (isInRange(nx, ny)) {
                if (map[nx][ny] >= 1) {
                    return map[nx][ny];
                } else {
                    map[nx][ny] = map[x][y];
                    map[x][y] = 0;
                    this.x = nx;
                    this.y = ny;
                    return -1;
                }
            }
            return -2;
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
