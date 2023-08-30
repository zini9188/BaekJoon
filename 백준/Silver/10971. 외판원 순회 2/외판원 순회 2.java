import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static int N;
    private static int[][] A;
    private static boolean[] visited;
    private static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        A = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            dfs(i, i, 0);
        }
        System.out.println(answer);
    }

    private static void dfs(int now, int start, int total) {
        if (isAllVisited()) {
            if (A[now][start] == 0) return;
            answer = Math.min(answer, total + A[now][start]);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i] && A[now][i] != 0) {
                visited[i] = true;
                dfs(i, start, total + A[now][i]);
                visited[i] = false;
            }
        }
    }

    private static boolean isAllVisited() {
        for (int i = 0; i < N; i++) {
            if (!visited[i]) return false;
        }
        return true;
    }
}