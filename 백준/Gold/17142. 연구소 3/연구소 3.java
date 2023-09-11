import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[][] lab;
    static List<Virus> virus = new LinkedList<>();
    static boolean[] selected;
    static Virus[] selectedVirus;
    static int N, M;
    static int ans = Integer.MAX_VALUE;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int empty = 0;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        lab = new int[N][N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                lab[i][j] = Integer.parseInt(tokenizer.nextToken());
                if (lab[i][j] == 2) {
                    virus.add(new Virus(i, j, 0));
                } else if (lab[i][j] != 1) {
                    empty++;
                }
            }
        }

        if (empty == 0) {
            sb.append("0");
        } else {
            selectedVirus = new Virus[M];
            selected = new boolean[virus.size()];
            findVirus(0, 0);
            sb.append(ans == Integer.MAX_VALUE ? -1 : ans);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void findVirus(int depth, int start) {
        if (depth == M) {
            ans = Math.min(spread(), ans);
            return;
        }

        for (int i = start; i < virus.size(); i++) {
            if (!selected[i]) {
                selected[i] = true;
                selectedVirus[depth] = virus.get(i);
                findVirus(depth + 1, i + 1);
                selected[i] = false;
                selectedVirus[depth] = null;
            }
        }
    }

    private static int spread() {
        Queue<Virus> queue = new ArrayDeque<>(List.of(selectedVirus));
        boolean[][] visited = new boolean[N][N];
        for (Virus selectedVirus : selectedVirus) {
            visited[selectedVirus.x][selectedVirus.y] = true;
        }

        int count = 0;
        while (!queue.isEmpty()) {
            Virus v = queue.poll();

            for (int dir = 0; dir < 4; dir++) {
                int nx = v.x + dx[dir];
                int ny = v.y + dy[dir];
                if (isInRange(nx, ny) && !visited[nx][ny] && lab[nx][ny] != 1) {
                    if (lab[nx][ny] == 0) {
                        count++;
                    }

                    if (count == empty) {
                        return v.t + 1;
                    }

                    visited[nx][ny] = true;
                    queue.add(new Virus(nx, ny, v.t + 1));
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    static class Virus {
        int x, y, t;

        public Virus(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }
}