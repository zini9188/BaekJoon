import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Queue<Task> times = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int Ti = Integer.parseInt(st.nextToken());
            int Si = Integer.parseInt(st.nextToken());
            times.add(new Task(Ti, Si));
        }

        int time = Integer.MAX_VALUE;
        while (!times.isEmpty()) {
            Task task = times.poll();

            if (time > task.s) {
                time = task.s;
            }

            time -= task.t;
        }

        sb.append(time < 0 ? -1 : time);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Task implements Comparable<Task> {
        int t, s;

        public Task(int t, int s) {
            this.t = t;
            this.s = s;
        }

        @Override
        public int compareTo(Task o) {
            return o.s - s;
        }
    }
}