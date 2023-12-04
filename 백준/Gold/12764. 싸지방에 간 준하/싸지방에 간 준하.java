import java.io.*;
import java.util.*;
import java.util.stream.IntStream;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] count = new int[N];
        Queue<Integer> numQueue = new PriorityQueue<>(Comparator.comparingInt(o -> o));
        Queue<Using> usingQueue = new PriorityQueue<>();

        ArrayList<Using> input = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(tokenizer.nextToken());
            int Q = Integer.parseInt(tokenizer.nextToken());

            numQueue.add(i);

            input.add(new Using(P, Q));
        }
        input.sort(Comparator.comparingInt(o -> o.start));

        int index = 0;
        for (Using using : input) {
            while (!usingQueue.isEmpty() && usingQueue.peek().end < using.start) {
                numQueue.add(usingQueue.peek().idx);
                usingQueue.poll();
            }

            if (!numQueue.isEmpty()) {
                int idx = numQueue.poll();
                usingQueue.add(new Using(using.start, using.end, idx));
                count[idx]++;
                if (index < idx) {
                    index = idx;
                }
            }
        }

        sb.append(index + 1).append("\n");
        IntStream.range(0, index + 1).forEach(
                o-> sb.append(count[o]).append(" ")
        );

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Using implements Comparable<Using> {
        int start, end, idx;

        public Using(int start, int end, int idx) {
            this.start = start;
            this.end = end;
            this.idx = idx;
        }

        public Using(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Using o) {
            return this.end - o.end;
        }
    }
}