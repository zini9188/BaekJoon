import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] parent;
    static int[] days;
    static int[] ground;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, 1, 0, -1};
    static int N, M;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        int Q = Integer.parseInt(tokenizer.nextToken());

        parent = new int[N * M + 1];
        days = new int[N * M + 1];
        ground = new int[N * M + 1];
        for (int i = 0; i <= N * M; i++) {
            parent[i] = i;
        }

        for (int x = 1; x <= N; x++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int y = 1; y <= M; y++) {
                ground[getSequence(x, y)] = Integer.parseInt(tokenizer.nextToken());
            }
        }

        boolean[] isRained = new boolean[N * M + 1];
        for (int day = 1; day <= Q; day++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());

            int ab = getSequence(a, b);
            ground[ab] -= c;
            days[ab] = day;
            isRained[ab] = true;

            for (int dir = 0; dir < 4; dir++) {
                int nx = a + dx[dir];
                int ny = b + dy[dir];
                int xy = getSequence(nx, ny);
                if (isInRange(nx, ny) && isRained[xy]) {
                    int current = find(ab);
                    int next = find(xy);
                    if (next != current) {
                        union(ab, xy);
                    } else {
                        if (ground[ab] < ground[xy]) {
                            parent[ab] = ab;
                            union(ab, xy);
                        } else {
                            if (days[ab] < days[xy]) {
                                parent[ab] = ab;
                                union(ab, xy);
                            }
                        }
                    }
                }
            }
            int p = find(ab);
            sb.append(toX(p)).append(" ").append(toY(p)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void union(int ab, int xy) {
        int nab = find(ab);
        int nxy = find(xy);

        int a = toX(nab);
        int b = toY(nab);
        int x = toX(nxy);
        int y = toY(nxy);

        ab = getSequence(a, b);
        xy = getSequence(x, y);

        if (ground[ab] < ground[xy]) {
            parent[nxy] = nab;
        } else if (ground[ab] > ground[xy]) {
            parent[nab] = nxy;
        } else {
            if (days[ab] < days[xy]) {
                parent[nxy] = nab;
            } else {
                parent[nab] = nxy;
            }
        }
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static int toX(int x) {
        return (x - 1) / M + 1;
    }

    private static int toY(int y) {
        return (y - 1) % M + 1;
    }

    private static int getSequence(int x, int y) {
        return (x - 1) * M + y;
    }

    private static boolean isInRange(int x, int y) {
        return x >= 1 && y >= 1 && x <= N && y <= M;
    }
}