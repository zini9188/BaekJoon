import java.util.Scanner;

class Main {
    static int R, C;
    static String[] lines;
    static int result;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static boolean[] visited;

    public static void main(String args[]) throws Exception {
        //System.setIn(new FileInputStream("src/src2/input.txt"));
        Scanner scanner = new Scanner(System.in);
        R = scanner.nextInt();
        C = scanner.nextInt();
        result = 0;
        visited = new boolean[27];
        lines = new String[R];
        for (int i = 0; i < R; i++) {
            lines[i] = scanner.next();
        }
        visited[lines[0].charAt(0) - 'A'] = true;
        dfs(0, 0, 1);
        System.out.printf(String.valueOf(result));

    }

    private static void dfs(int x, int y, int cnt) {
        result = Math.max(result, cnt);
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < R && ny < C) {
                int nextAlpha = lines[nx].charAt(ny) - 'A';
                if (!visited[nextAlpha]) {
                    visited[nextAlpha] = true;
                    dfs(nx, ny, cnt + 1);
                    visited[nextAlpha] = false;
                }
            }
        }
    }
}