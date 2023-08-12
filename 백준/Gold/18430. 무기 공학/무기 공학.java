import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] dx = {0, 1, 0, -1, -1, 0, 0, 1};
    static int[] dy = {-1, 0, -1, 0, 0, 1, 1, 0};
    static int N, M;
    static int[][] wood;
    static boolean[][] visited;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        wood = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                wood[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        dfs(0, 0);

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int index, int power) {
        if (index == N * M) {
            answer = Math.max(answer, power);
            return;
        }

        int x = index / M;
        int y = index % M;
        if (!visited[x][y]) {
            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir * 2];
                int ny = y + dy[dir * 2];
                int nx2 = x + dx[dir * 2 + 1];
                int ny2 = y + dy[dir * 2 + 1];
                if (isInRange(nx, ny) && isInRange(nx2, ny2) && !visited[nx][ny] && !visited[nx2][ny2]) {
                    visited[nx2][ny2] = true;
                    visited[nx][ny] = true;
                    visited[x][y] = true;
                    dfs(index + 1, power + (wood[x][y] * 2) + wood[nx][ny] + wood[nx2][ny2]);
                    visited[x][y] = false;
                    visited[nx][ny] = false;
                    visited[nx2][ny2] = false;
                }
            }
        }
        dfs(index + 1, power);
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < M;
    }
}