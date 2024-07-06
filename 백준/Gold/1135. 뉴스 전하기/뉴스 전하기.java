import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static List<List<Integer>> graph;
    private static int[] child;
    private static final Comparator<Integer> comparator = (o1, o2) -> child[o2] - child[o1];

    public static void main(String[] args) throws IOException {
        int k = Integer.parseInt(br.readLine());

        graph = new ArrayList<>();
        child = new int[k + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            graph.add(new ArrayList<>());
            int boss = Integer.parseInt(st.nextToken());
            if (boss == -1) {
                continue;
            }

            graph.get(boss).add(i);
        }

        int ans = dfs(0);
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static int dfs(int cur) {
        for (Integer next : graph.get(cur)) {
            child[next] += 1 + dfs(next);
        }
        graph.get(cur).sort(comparator);
        int temp = 0;
        for (int i = 0; i < graph.get(cur).size(); i++) {
            Integer next = graph.get(cur).get(i);
            child[next] += i;
            temp = Math.max(temp, child[next]);
        }
        return temp;
    }
}