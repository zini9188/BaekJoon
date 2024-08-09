import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static Set<Integer> set = new HashSet<>();
    private static int n, k;
    private static int[] arr;
    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        n = read();
        k = read();

        arr = new int[n];
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            arr[i] = read();
        }

        dfs(0, "");

        sb.append(set.size());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int x, String num) {
        if (x == k) {
            set.add(Integer.valueOf(num));
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(x + 1, num + arr[i]);
                visited[i] = false;
            }
        }
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}