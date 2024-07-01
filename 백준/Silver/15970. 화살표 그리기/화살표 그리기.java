import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static int N;
    private static List<List<Integer>> positions;

    public static void main(String[] args) throws IOException {
        input();
        solution();
        output();
    }

    private static void solution() {
        for (int i = 0; i <= N; i++) {
            Collections.sort(positions.get(i));
        }

        int ans = 0;
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < positions.get(i).size(); j++) {
                if (j == 0) {
                    ans += positions.get(i).get(j + 1) - positions.get(i).get(j);
                } else if (j == positions.get(i).size() - 1) {
                    ans += positions.get(i).get(j) - positions.get(i).get(j - 1);
                } else {
                    int t = positions.get(i).get(j + 1) - positions.get(i).get(j);
                    int f = positions.get(i).get(j) - positions.get(i).get(j - 1);

                    ans += Math.min(t, f);
                }
            }
        }
        sb.append(ans);
    }

    private static void output() throws IOException {
        bw.write(sb.toString());
        bw.close();
    }

    private static void input() throws IOException {
        N = Integer.parseInt(br.readLine());
        positions = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            positions.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int point = Integer.parseInt(st.nextToken());
            int color = Integer.parseInt(st.nextToken());

            positions.get(color).add(point);
        }
    }
}