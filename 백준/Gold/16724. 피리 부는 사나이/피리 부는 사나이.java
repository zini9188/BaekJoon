import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] parent;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[][] map;
    static int N, M;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        map = new char[N][M];
        parent = new int[N * M];
        for (int x = 0; x < N; x++) {
            map[x] = br.readLine().toCharArray();
            for (int y = 0; y < M; y++) {
                int p = getPoint(x, y);
                parent[p] = p;
            }
        }

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < M; y++) {
                int dir = getDir(map[x][y]);
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                union(getPoint(x, y), getPoint(nx, ny));
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int element : parent) {
            set.add(find(element));
        }

        sb.append(set.size());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }
        parent[a] = b;
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static int getPoint(int x, int y) {
        return x * M + y;
    }

    private static int getDir(char dir) {
        if (dir == 'U') {
            return 0;
        }
        if (dir == 'D') {
            return 1;
        }
        if (dir == 'L') {
            return 2;
        }
        return 3;
    }
}