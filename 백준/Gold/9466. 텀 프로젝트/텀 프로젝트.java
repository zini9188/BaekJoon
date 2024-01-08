import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int n, ans;
    static boolean[] visited, success;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            n = Integer.parseInt(br.readLine());
            arr = new int[n + 1];
            st = new StringTokenizer(br.readLine());
            visited = new boolean[n + 1];
            success = new boolean[n + 1];
            for (int j = 1; j <= n; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }

            ans = n;
            for (int j = 1; j <= n; j++) {
                if (!visited[j]) {
                    dfs(j);
                }
            }
            sb.append(ans).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int x) {
        visited[x] = true;

        int nx = arr[x];
        if (!visited[nx]) {
            dfs(nx);
        } else if (!success[nx]) {
            ans--;
            
            while (nx != x) {
                nx = arr[nx];
                ans--;
            }
        }
        success[x] = true;
    }
}