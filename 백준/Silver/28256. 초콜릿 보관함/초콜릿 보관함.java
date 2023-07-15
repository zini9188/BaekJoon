import java.io.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static boolean[][] visited;
    static char[][] storage;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer;
        int T = Integer.parseInt(br.readLine());

        StringBuilder builder = new StringBuilder();
        for (int testCase = 0; testCase < T; testCase++) {
            storage = new char[3][3];
            for (int i = 0; i < 3; i++) {
                String line = br.readLine();
                storage[i] = line.toCharArray();
            }

            tokenizer = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int[] actual = new int[4];
            for (int i = 0; i < n; i++) {
                actual[i] = Integer.parseInt(tokenizer.nextToken());
            }

            visited = new boolean[3][3];
            int[] expect = new int[4];
            int index = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (storage[i][j] == 'O' && !visited[i][j]) {
                        expect[index++] = bfs(i, j);
                    }
                }
            }

            Arrays.sort(expect);
            Arrays.sort(actual);
            int ans = check(actual, expect);
            builder.append(ans).append("\n");
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int check(int[] actual, int[] expect) {
        for (int i = 0; i < 4; i++) {
            if (actual[i] != expect[i]) {
                return 0;
            }
        }
        return 1;
    }

    private static int bfs(int x, int y) {
        Queue<Point> queue = new LinkedList<>();
        visited[x][y] = true;
        queue.add(new Point(x, y));
        int count = 1;
        while (!queue.isEmpty()) {
            Point point = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = point.x + dx[dir];
                int ny = point.y + dy[dir];
                if (isInRange(nx, ny) && !visited[nx][ny] && storage[nx][ny] == 'O') {
                    visited[nx][ny] = true;
                    count++;
                    queue.add(new Point(nx, ny));
                }
            }
        }
        return count;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < 3 && y < 3;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
