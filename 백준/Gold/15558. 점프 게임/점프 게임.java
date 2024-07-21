import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[2][N + 1];
        for (int i = 0; i < 2; i++) {
            String line = br.readLine();
            for (int j = 1; j <= N; j++) {
                map[i][j] = line.charAt(j - 1) - '0';
            }
        }

        sb.append(solution(N, K, map));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int solution(int N, int K, int[][] map) {
        Queue<Position> q = new ArrayDeque<>();
        q.add(new Position(0, 1, 0));
        boolean[][] visited = new boolean[2][N + 1];
        visited[0][1] = true;
        while (!q.isEmpty()) {
            Position p = q.poll();

            if (p.pos <= p.time) {
                continue;
            }

            if (p.pos + 1 > N) {
                return 1;
            } else if (!visited[p.lr][p.pos + 1] && map[p.lr][p.pos + 1] == 1) {
                visited[p.lr][p.pos + 1] = true;
                q.add(new Position(p.lr, p.pos + 1, p.time + 1));
            }

            if (p.pos - 1 > 0 && !visited[p.lr][p.pos - 1] && map[p.lr][p.pos - 1] == 1) {
                visited[p.lr][p.pos - 1] = true;
                q.add(new Position(p.lr, p.pos - 1, p.time + 1));
            }

            if (p.pos + K > N) {
                return 1;
            } else if (!visited[1 - p.lr][p.pos + K] && map[1 - p.lr][p.pos + K] == 1) {
                visited[1 - p.lr][p.pos + K] = true;
                q.add(new Position(1 - p.lr, p.pos + K, p.time + 1));
            }
        }

        return 0;
    }

    private static class Position {

        int lr, pos, time;

        public Position(int lr, int pos, int time) {
            this.lr = lr;
            this.pos = pos;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "lr=" + lr +
                    ", pos=" + pos +
                    ", time=" + time +
                    '}';
        }
    }
}