import java.io.*;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        boolean[][] visited = new boolean[N][N];
        Queue<Point> queue = new LinkedList<>();
        PriorityQueue<Integer> answer = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < line.length(); j++) {
                map[i][j] = line.charAt(j) == '0' ? 0 : 1;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    queue.add(new Point(i, j));
                    visited[i][j] = true;
                    int count = 1;
                    while (!queue.isEmpty()) {
                        Point point = queue.poll();
                        int x = point.x;
                        int y = point.y;
                        for (int k = 0; k < 4; k++) {
                            int nx = x + dx[k];
                            int ny = y + dy[k];
                            if (isInRange(N, nx, ny) && !visited[nx][ny] && map[nx][ny] == 1) {
                                visited[nx][ny] = true;
                                queue.add(new Point(nx, ny));
                                count++;
                            }
                        }
                    }
                    answer.add(count);
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(answer.size()).append("\n");
        while (!answer.isEmpty()) {
            builder.append(answer.poll()).append("\n");
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static boolean isInRange(int range, int x, int y) {
        return x >= 0 && y >= 0 && x < range && y < range;
    }
}