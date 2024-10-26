import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int[] hp;
    private static int[][] arr = {{9, 3, 1}, {9, 1, 3}, {1, 3, 9}, {1, 9, 3}, {3, 9, 1}, {3, 1, 9}};
    private static int ans = Integer.MAX_VALUE;
    private static boolean[][][] visited;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        hp = new int[3];
        visited = new boolean[61][61][61];
        for (int i = 0; i < N; i++) {
            hp[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = N; i < 3; i++) {
            hp[i] = 0;
        }

        dfs(0, hp[0], hp[1], hp[2]);

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int depth, int a, int b, int c) {
        a = Math.max(0, a);
        b = Math.max(0, b);
        c = Math.max(0, c);

        if (a == 0 && b == 0 && c == 0) {
            ans = Math.min(ans, depth);
            return;
        }

        int[] h = {a, b, c};
        Arrays.sort(h);
        a = h[2];
        b = h[1];
        c = h[0];

        if (visited[a][b][c]) {
            return;
        }
        visited[a][b][c] = true;

        if (depth > ans) {
            return;
        }

        for (int i = 0; i < 6; i++) {
            dfs(depth + 1, a - arr[i][0], b - arr[i][1], c - arr[i][2]);
        }
    }
}