import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] degree = new int[N + 1];
        int[] times = new int[N + 1];
        Queue<Task> q = new PriorityQueue<>();
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int task = Integer.parseInt(st.nextToken());
            int cnt = Integer.parseInt(st.nextToken());

            degree[i] = cnt;
            times[i] = task;
            for (int j = 0; j < cnt; j++) {
                graph.get(Integer.parseInt(st.nextToken())).add(i);
            }
        }

        for (int i = 1; i < N + 1; i++) {
            if (degree[i] == 0) {
                q.add(new Task(i, times[i]));
            }
        }

        int time = 0;
        while (!q.isEmpty()) {
            Task task = q.poll();

            time = Math.max(time, task.time);
            for (Integer next : graph.get(task.idx)) {
                if (--degree[next] == 0) {
                    q.add(new Task(next, task.time + times[next]));
                }
            }
        }

        sb.append(time);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Task implements Comparable<Task> {

        int idx, time;

        public Task(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Task o) {
            return time - o.time;
        }
    }
}