import java.io.*;
import java.util.*;

public class Main {

    static final char EMPTY = '.';
    static final char WALL = '*';
    static final char DOC = '$';
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static char[][] building;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int w, h;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            tokenizer = new StringTokenizer(br.readLine());
            h = Integer.parseInt(tokenizer.nextToken());
            w = Integer.parseInt(tokenizer.nextToken());
            building = new char[h + 2][w + 2];
            for (int x = 1; x <= h; x++) {
                String line = br.readLine();
                for (int y = 1; y <= w; y++) {
                    building[x][y] = line.charAt(y - 1);
                }
            }

            String firstKey = br.readLine();
            int key = 1;
            if (!firstKey.equals("0")) {
                for (char c : firstKey.toCharArray()) {
                    key |= (1 << c - 'a' + 1);
                }
            }

            int maxDocument = 0;
            Queue<Sangeun> queue = new ArrayDeque<>();
            queue.add(new Sangeun(0, 0));
            int[][] visited = new int[h + 2][w + 2];
            visited[0][0] = key;
            while (!queue.isEmpty()) {
                Sangeun cur = queue.poll();

                for (int dir = 0; dir < 4; dir++) {
                    int nx = cur.x + dx[dir];
                    int ny = cur.y + dy[dir];

                    if (outRange(nx, ny) || visited[nx][ny] == key || building[nx][ny] == WALL) {
                        continue;
                    }

                    if (Character.isUpperCase(building[nx][ny])) {
                        if (cantOpenDoor(key, building[nx][ny])) {
                            continue;
                        }
                    }

                    visited[nx][ny] = key;
                    if (Character.isLowerCase(building[nx][ny])) {
                        key |= (1 << building[nx][ny] - 'a' + 1);
                    }

                    if (building[nx][ny] == DOC) {
                        maxDocument++;
                    }

                    building[nx][ny] = EMPTY;
                    queue.add(new Sangeun(nx, ny));
                }
            }
            sb.append(maxDocument).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean outRange(int x, int y) {
        return x < 0 || y < 0 || x > h + 1 || y > w + 1;
    }

    private static boolean cantOpenDoor(int key, char door) {
        return (key >> (door - 'A' + 1) & 1) == 0;
    }

    private static class Sangeun {
        int x, y;

        public Sangeun(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}