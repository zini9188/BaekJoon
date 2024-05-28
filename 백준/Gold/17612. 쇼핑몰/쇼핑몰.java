import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Counter> counters = new PriorityQueue<>();
        for (int i = 0; i < K; i++) {
            counters.add(new Counter(i + 1, 1));
        }

        Queue<Info> out = new PriorityQueue<>();
        int outer = 1;
        int currentTime = 1;
        long sum = 0;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            long id = Long.parseLong(st.nextToken());
            int buy = Integer.parseInt(st.nextToken());

            if (counters.isEmpty()) {
                if (!out.isEmpty()) {
                    Info info = out.poll();
                    counters.add(new Counter(info.index, info.time));
                    sum += info.id * (outer++);
                    currentTime = info.time;
                }

                while (!out.isEmpty() && out.peek().time == currentTime) {
                    Info info = out.poll();
                    counters.add(new Counter(info.index, info.time));
                    sum += info.id * (outer++);
                }
            }

            if (!counters.isEmpty()) {
                Counter counter = counters.poll();
                Info e = new Info(id, buy + counter.time, counter.idx);
                out.add(e);
            }
        }

        while (!out.isEmpty()) {
            Info info = out.poll();
            counters.add(new Counter(info.index, info.time));
            sum += info.id * (outer++);
        }

        sb.append(sum);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Info implements Comparable<Info> {

        long id;
        int index, time;

        public Info(long id, int time, int index) {
            this.id = id;
            this.time = time;
            this.index = index;
        }

        @Override
        public int compareTo(Info o) {
            if (time == o.time) {
                return o.index - index;
            }

            return time - o.time;
        }
    }

    static class Counter implements Comparable<Counter> {

        int idx, time;

        public Counter(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Counter o) {
            return idx - o.idx;
        }
    }
}