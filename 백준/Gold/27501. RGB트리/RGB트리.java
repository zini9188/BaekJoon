import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static final int RED = 0, GREEN = 1, BLUE = 2;
    private static final char[] color = {'R', 'G', 'B'};
    private static StringTokenizer st;
    private static List<List<Integer>> graph;
    private static int[][] rgb;
    private static boolean[] visited;
    private static int[] colors;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void solution() {
        dfs(1);

        visited = new boolean[visited.length];
        int maxIdx = -1, max = -1;
        for (int i = 0; i < 3; i++) {
            if (rgb[1][i] > max) {
                maxIdx = i;
                max = rgb[1][i];
            }
        }
        findDFS(1, maxIdx);

        int ans = Math.max(Math.max(rgb[1][RED], rgb[1][BLUE]), rgb[1][GREEN]);
        sb.append(ans).append("\n");
        for (int i = 1; i < colors.length; i++) {
            sb.append(color[colors[i]]);
        }
    }

    private static void findDFS(int cur, int color) {
        visited[cur] = true;
        colors[cur] = color;

        for (Integer next : graph.get(cur)) {
            if (visited[next]) {
                continue;
            }

            if (color == RED) {
                findDFS(next, rgb[next][GREEN] < rgb[next][BLUE] ? BLUE : GREEN);
            } else if (color == GREEN) {
                findDFS(next, rgb[next][RED] < rgb[next][BLUE] ? BLUE : RED);
            } else if (color == BLUE) {
                findDFS(next, rgb[next][GREEN] < rgb[next][RED] ? RED : GREEN);
            }
        }
    }

    private static void dfs(int cur) {
        visited[cur] = true;
        for (Integer next : graph.get(cur)) {
            if (visited[next]) {
                continue;
            }
            dfs(next);

            rgb[cur][RED] += Math.max(rgb[next][GREEN], rgb[next][BLUE]);
            rgb[cur][GREEN] += Math.max(rgb[next][RED], rgb[next][BLUE]);
            rgb[cur][BLUE] += Math.max(rgb[next][RED], rgb[next][GREEN]);
        }
    }

    private static void input() throws IOException {
        int N = read();

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            int from = read();
            int to = read();
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        rgb = new int[N + 1][3];
        for (int i = 1; i <= N; i++) {
            for (int color = 0; color < 3; color++) {
                rgb[i][color] = read();
            }
        }

        visited = new boolean[N + 1];
        colors = new int[N + 1];
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}