import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static final int MAX_POSITION = 11;
    static int[][] ability;
    static boolean[] visited;
    static int ans;

    public static void main(String[] args) throws IOException {
        int C = Integer.parseInt(br.readLine());
        for (int c = 0; c < C; c++) {

            ans = 0;
            ability = new int[MAX_POSITION][MAX_POSITION];
            visited = new boolean[MAX_POSITION];
            for (int i = 0; i < MAX_POSITION; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < MAX_POSITION; j++) {
                    ability[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dfs(0, 0);
            sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int depth, int ab) {
        if (depth == MAX_POSITION) {
            ans = Math.max(ans, ab);
            return;
        }

        for (int i = 0; i < MAX_POSITION; i++) {
            if (!visited[i] && ability[depth][i] > 0) {
                visited[i] = true;
                dfs(depth + 1, ab + ability[depth][i]);
                visited[i] = false;
            }
        }
    }
}