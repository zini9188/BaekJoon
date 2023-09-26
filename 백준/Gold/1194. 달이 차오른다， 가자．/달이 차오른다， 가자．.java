import java.io.*;
import java.util.*;

public class Main {

    static final char EMPTY = '.';
    static final char WALL = '#';
    static final char EXIT = '1';
    static final char CURRENT_POSITION = '0';
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;
    static char[][] maze;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        maze = new char[N][M];
        Person p = null;
        for (int i = 0; i < N; i++) {
            maze[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == CURRENT_POSITION) {
                    p = new Person(i, j, 0, 0);
                    maze[i][j] = EMPTY;
                }
            }
        }
        
        sb.append(bfs(p));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs(Person p) {
        Queue<Person> queue = new ArrayDeque<>();
        queue.add(p);
        boolean[][][] visited = new boolean[1 << 6][N][M];
        visited[0][p.x][p.y] = true;
        while (!queue.isEmpty()) {
            Person cur = queue.poll();

            if (maze[cur.x][cur.y] == EXIT) {
                return cur.move;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = cur.x + dx[dir];
                int ny = cur.y + dy[dir];

                if (!isInRange(nx, ny) || visited[cur.keys][nx][ny] || maze[nx][ny] == WALL) {
                    continue;
                }

                if (maze[nx][ny] == EMPTY || maze[nx][ny] == EXIT) {
                    visited[cur.keys][nx][ny] = true;
                    queue.add(new Person(nx, ny, cur.move + 1, cur.keys));
                } else if (maze[nx][ny] <= 'F') {
                    int door = 1 << (maze[nx][ny] - 'A');
                    if ((cur.keys & door) > 0) {
                        visited[cur.keys][nx][ny] = true;
                        queue.add(new Person(nx, ny, cur.move + 1, cur.keys));
                    }
                } else {
                    int nKeys = (1 << (maze[nx][ny] - 'a')) | cur.keys;
                    if (!visited[nKeys][nx][ny]) {
                        visited[nKeys][nx][ny] = true;
                        visited[cur.keys][nx][ny] = true;
                        queue.add(new Person(nx, ny, cur.move + 1, nKeys));
                    }
                }
            }
        }
        return -1;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }

    static class Person {
        int x, y, move, keys;

        public Person(int x, int y, int move, int keys) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.keys = keys;
        }
    }
}