import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        int important, index;

        public Pair(int important, int index) {
            this.important = important;
            this.index = index;
        }
    }

    static int T, N, M;
    static Queue<Pair> queue;
    static PriorityQueue<Integer> priorityQueue;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder stringBuilder = new StringBuilder();
        T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            N = Integer.parseInt(tokenizer.nextToken());
            M = Integer.parseInt(tokenizer.nextToken());
            tokenizer = new StringTokenizer(br.readLine());
            priorityQueue = new PriorityQueue<>((o1, o2) -> o2 - o1);
            queue = new LinkedList<>();
            for (int j = 0; j < N; j++) {
                int importance = Integer.parseInt(tokenizer.nextToken());
                queue.add(new Pair(importance, j));
                priorityQueue.add(importance);
            }
            stringBuilder.append(solution()).append("\n");
        }
        bw.write(stringBuilder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution() {
        int result = 1;
        while (!queue.isEmpty() && !priorityQueue.isEmpty()) {
            if (queue.peek().important == priorityQueue.peek()) {
                if (queue.peek().index == M) {
                    return result;
                }
                result++;
                queue.poll();
                priorityQueue.poll();
            } else {
                queue.add(queue.poll());
            }
        }
        return -1;
    }
}