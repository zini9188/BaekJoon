import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    private static int N;
    private static int[] arr;
    private static int s;
    private static boolean[] visited;
    private static int res;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        s = Integer.parseInt(br.readLine()) - 1;

        visited = new boolean[N + 1];
        dfs(s);

        sb.append(res);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static void dfs(int current) {
        res++;
        visited[current] = true;
        int left = current - arr[current];
        int right = current + arr[current];
        if (inRange(left) && !visited[left]) {
            dfs(left);
        }

        if (inRange(right) && !visited[right]) {
            dfs(right);
        }
    }

    public static boolean inRange(int index) {
        return index >= 0 && index < N;
    }
}