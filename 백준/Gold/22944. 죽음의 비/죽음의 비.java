import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static char[][] map;
    static int N, H, D;
    static final char UMB = 'U', SAFE = 'E', EMPTY = '.', CUR = 'S';
    static int[] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new char[N][N];

        Human human = null;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j);
                if (map[i][j] == CUR) {
                    human = new Human(i, j, 0, H, 0);
                }
            }
        }

        int[][] dist = new int[N][N];
        Queue<Human> q = new ArrayDeque<>();
        q.add(human);
        dist[human.x][human.y] = human.durability + human.hp;
        sb.append(bfs(q, dist));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int bfs(Queue<Human> q, int[][] dist) {
        while (!q.isEmpty()) {
            Human cur = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int durability = cur.durability;
                int hp = cur.hp;

                if (outRange(nx, ny)) {
                    continue;
                }

                if (map[nx][ny] == UMB) {
                    durability = D;
                } else if (map[nx][ny] == SAFE) {
                    return cur.cnt + 1;
                }

                if (durability > 0) {
                    durability--;
                } else if (durability == 0) {
                    if (--hp == 0) {
                        continue;
                    }
                }

                if (durability + hp > dist[nx][ny]) {
                    q.add(new Human(nx, ny, durability, hp, cur.cnt + 1));
                    dist[nx][ny] = hp + durability;
                }
            }
        }
        return -1;
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x >= N || y >= N;
    }

    static class Human {
        int x, y, durability, hp, cnt;

        public Human(int x, int y, int durability, int hp, int cnt) {
            this.x = x;
            this.y = y;
            this.durability = durability;
            this.hp = hp;
            this.cnt = cnt;
        }
    }
}