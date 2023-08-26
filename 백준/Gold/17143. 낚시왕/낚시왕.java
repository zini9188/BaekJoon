import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Area[][] areas;
    static Queue<Shark> sharks;
    static int R, C, M;
    static int[] dx = {0, -1, 1, 0, 0};
    static int[] dy = {0, 0, 0, 1, -1};
    static int ans = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        R = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        sharks = new LinkedList<>();
        areas = new Area[R + 1][C + 1];
        init();

        char alpha = 'A';
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            int s = Integer.parseInt(tokenizer.nextToken());
            int d = Integer.parseInt(tokenizer.nextToken());
            int z = Integer.parseInt(tokenizer.nextToken());
            Shark shark = new Shark(r, c, s, d, z, alpha++);
            sharks.add(shark);
            areas[r][c].sharks.add(shark);
            areas[r][c].max = shark.size;
        }

        for (int y = 1; y <= C; y++) {
            fishingSharks(y);
            init();
            moveSharks();
            eatingSharks();
        }

        bw.write(ans + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void init() {
        for (int i = 0; i < R + 1; i++) {
            for (int j = 0; j < C + 1; j++) {
                areas[i][j] = new Area(0);
            }
        }
    }

    private static void eatingSharks() {
        for (int x = 1; x <= R; x++) {
            for (int y = 1; y <= C; y++) {
                int size = areas[x][y].sharks.size();
                if (size == 0) {
                    continue;
                }
                if (size == 1) {
                    sharks.add(areas[x][y].sharks.get(0));
                } else {
                    for (int i = 0; i < areas[x][y].sharks.size(); i++) {
                        if (areas[x][y].sharks.get(i).size == areas[x][y].max) {
                            Shark shark = areas[x][y].sharks.get(i);
                            areas[x][y].sharks.clear();
                            areas[x][y].sharks.add(shark);
                            sharks.add(shark);
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void fishingSharks(int y) {
        for (int x = 1; x <= R; x++) {
            if (!areas[x][y].sharks.isEmpty()) {
                Shark shark = areas[x][y].sharks.remove(0);
                sharks.remove(shark);
                areas[x][y].max = 0;
                ans += shark.size;
                break;
            }
        }
    }

    private static void moveSharks() {
        int size = sharks.size();
        for (int i = 0; !sharks.isEmpty() && i < size; i++) {
            Shark current = sharks.poll();
            int x = current.x;
            int y = current.y;
            int dir = current.dir;
            int speed = current.speed;

            while (speed != 0) {
                int move;
                if (dir == 1) {
                    move = Math.min(speed, x - 1);
                } else if (dir == 2) {
                    move = Math.min(speed, R - x);
                } else if (dir == 3) {
                    move = Math.min(speed, C - y);
                } else {
                    move = Math.min(speed, y - 1);
                }
                x += dx[dir] * move;
                y += dy[dir] * move;
                speed -= move;
                if (isArriveEnd(x, y, dir)) {
                    dir = changeDir(dir);
                }
            }

            current.x = x;
            current.y = y;
            current.dir = dir;
            areas[x][y].sharks.add(current);
            areas[x][y].max = Math.max(areas[x][y].max, current.size);
        }
    }

    private static int changeDir(int dir) {
        return dir == 1 ? 2 :
                dir == 2 ? 1 :
                        dir == 3 ? 4 : 3;
    }

    static boolean isArriveEnd(int x, int y, int dir) {
        if(dir == 1){
            return x == 1;
        }else if(dir == 2){
            return x == R;
        }else if(dir == 3){
            return y == C;
        }else {
            return y == 1;
        }
    }

    static class Shark {
        int x, y, speed, dir, size;
        char name;

        public Shark(int x, int y, int speed, int dir, int size, char name) {
            this.x = x;
            this.y = y;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
            this.name = name;
        }
    }

    static class Area {
        List<Shark> sharks = new LinkedList<>();
        int max;

        public Area(int max) {
            this.max = max;
        }
    }
}