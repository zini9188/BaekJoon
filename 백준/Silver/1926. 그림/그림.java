import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(tokenizer.nextToken());
        int m = Integer.parseInt(tokenizer.nextToken());

        int[][] paper = new int[n][m];
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                paper[i][j] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        int[] ans = {0, 0};
        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (paper[i][j] == 1 && !visited[i][j]) {
                    Queue<int[]> q = new ArrayDeque<>();
                    q.add(new int[]{i, j});
                    visited[i][j] = true;
                    int cnt = 1;
                    while (!q.isEmpty()) {
                        int[] cur = q.poll();

                        for (int dir = 0; dir < 4; dir++) {
                            int nx = cur[0] + dx[dir];
                            int ny = cur[1] + dy[dir];
                            if (nx < 0 || ny < 0 || nx >= n || ny >= m || visited[nx][ny]) {
                                continue;
                            }

                            if (paper[nx][ny] == 1) {
                                visited[nx][ny] = true;
                                cnt++;
                                q.add(new int[]{nx, ny});
                            }
                        }
                    }

                    ans[0]++;
                    if (cnt > ans[1]) {
                        ans[1] = cnt;
                    }
                }
            }
        }

        sb.append(ans[0]).append("\n").append(ans[1]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}