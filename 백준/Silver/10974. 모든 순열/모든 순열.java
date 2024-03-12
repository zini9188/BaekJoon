import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int[] temp;
    static boolean[] visited;
    static List<String> ans = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        temp = new int[N];
        visited = new boolean[N];
        dfs(0);
        Collections.sort(ans);
        ans.forEach(o -> sb.append(o).append("\n"));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void dfs(int depth) {
        if (depth == N) {
            ans.add(Arrays.toString(temp).substring(1).replace(",", "").replace("]", ""));
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[i] = depth + 1;
                dfs(depth + 1);
                visited[i] = false;
                temp[i] = 0;
            }
        }
    }
}