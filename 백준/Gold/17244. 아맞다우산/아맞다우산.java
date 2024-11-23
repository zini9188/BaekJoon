import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        char[][] home = new char[N][M];
        int[] dx = {-1, 1, 0, 0}, dy = {0, 0, 1, -1};
        int[] target = new int[2];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            home[i] = br.readLine().toCharArray();
            for (int j = 0; j < M; j++) {
                if (home[i][j] == 'S') {
                    target[0] = i;
                    target[1] = j;
                    home[i][j] = '.';
                } else if (home[i][j] == 'X') {
                    ++cnt;
                    home[i][j] = (char) (cnt + '0');
                }
            }
        }

        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{target[0], target[1], 0, 0});
        boolean[][][] visited = new boolean[N][M][1 << cnt];
        while (!q.isEmpty()) {
            int[] info = q.poll();

            if (home[info[0]][info[1]] == 'E') {
                sb.append(info[2]);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nx = info[0] + dx[i];
                int ny = info[1] + dy[i];
                int nd = info[2];
                int has = info[3];

                if (home[nx][ny] == '#' || visited[nx][ny][has]) {
                    continue;
                }

                if (Character.isDigit(home[nx][ny])) {
                    has |= (1 << home[nx][ny] - '1');
                }

                if (visited[nx][ny][has]) {
                    continue;
                }

                if (home[nx][ny] == 'E' && has != (1 << cnt) - 1) {
                    continue;
                }

                visited[nx][ny][has] = true;
                q.add(new int[]{nx, ny, nd + 1, has});
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}