import java.io.*;
import java.util.*;

public class Main {
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static boolean[] visited;
    private static boolean isCycle;
    private static int N;
    private static int[] distance;
    private static Queue<Integer> queue = new ArrayDeque<>();
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();
    private static ArrayList<ArrayList<Integer>> graph;

    public static void main(String[] args) throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());

        visited = new boolean[N + 1];
        distance = new int[N + 1];
        Arrays.fill(distance, -1);

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        DFS(0, 1);

        BFS();

        for (int i = 1; i <= N; i++) {
            sb.append(distance[i]).append(" ");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void BFS() {
        int cnt = 1;
        while (!queue.isEmpty()) {
            int len = queue.size();
            for (int j = 0; j < len; j++) {
                int next = queue.poll();
                for (int i : graph.get(next)) {
                    if (distance[i] != -1) {
                        continue;
                    }
                    distance[i] = cnt;
                    queue.add(i);
                }
            }
            cnt++;
        }
    }

    private static void DFS(int prev, int cur) {
        visited[cur] = true;
        for (int next : graph.get(cur)) {
            if (visited[next] && next != prev) {
                isCycle = true;
                distance[next] = 0;
                queue.add(next);
                break;
            } else if (!visited[next]) {
                DFS(cur, next);
                if (isCycle) {
                    if (distance[next] == 0) {
                        isCycle = false;
                    } else {
                        distance[next] = 0;
                        queue.add(next);
                    }
                    return;
                }
            }
        }
    }
}