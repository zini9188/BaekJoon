import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        for (int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());
            String[] arr = new String[3];
            for (int j = 0; j < 3; j++) {
                arr[j] = st.nextToken();
            }

            sb.append("Data set ").append(i).append(": ").append(bfs(arr) ? "yes" : "no")
                    .append("\n");

        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean bfs(String[] arr) {
        boolean[][] visit = new boolean[201][201];
        Queue<int[]> q = new ArrayDeque<>();
        q.add(new int[]{0, 0, 0});
        while (!q.isEmpty()) {
            int[] idx = q.poll();
            int idx1 = idx[0];
            int idx2 = idx[1];
            int idx3 = idx[2];

            if (idx3 == arr[2].length()) {
                return true;
            }

            if (visit[idx1][idx2]) {
                continue;
            }

            if (idx1 < arr[0].length() && arr[0].charAt(idx1) == arr[2].charAt(idx3)) {
                visit[idx1][idx2] = true;
                q.add(new int[]{idx1 + 1, idx2, idx3 + 1});
            }

            if (idx2 < arr[1].length() && arr[1].charAt(idx2) == arr[2].charAt(idx3)) {
                visit[idx1][idx2] = true;
                q.add(new int[]{idx1, idx2 + 1, idx3 + 1});
            }
        }

        return false;
    }
}