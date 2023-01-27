import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
    static int N;
    static int[][] normal;
    static int[][] blind;
    static boolean[][] visitedNormal;
    static boolean[][] visitedBlind;
    static int[] dx = new int[]{1, 0, 0, -1};
    static int[] dy = new int[]{0, 1, -1, 0};
    static int normalRes = 0, blindRes = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        normal = new int[N + 1][N + 1];
        blind = new int[N + 1][N + 1];
        visitedNormal = new boolean[N + 1][N + 1];
        visitedBlind = new boolean[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            String line = br.readLine();
            for (int j = 1; j <= N; j++) {
                normal[i][j] = line.charAt(j - 1);
                blind[i][j] = line.charAt(j - 1);
                if (line.charAt(j - 1) == 'R') {
                    blind[i][j] = 'G';
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (!visitedNormal[i][j]) {
                    visitedNormal[i][j] = true;
                    bfs(i, j, visitedNormal, normal);
                    normalRes++;
                }
                
                if (!visitedBlind[i][j]) {
                    visitedBlind[i][j] = true;
                    bfs(i, j, visitedBlind, blind);
                    blindRes++;
                }
            }
        }

        bw.write(normalRes + " " + blindRes);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void bfs(int x, int y, boolean[][] visited, int[][] board) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x, y});
        while (!queue.isEmpty()) {
            int cx = queue.peek()[0];
            int cy = queue.poll()[1];
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                if (isInRange(nx, ny) && !visited[nx][ny] && board[cx][cy] == board[nx][ny]) {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static boolean isInRange(int x, int y) {
        return x > 0 && y > 0 && x <= N && y <= N;
    }
}