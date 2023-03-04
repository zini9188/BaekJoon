import java.io.*;
import java.util.*;

public class Main {
    static class Lecture {
        int start;
        int end;

        public Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Lecture> lectures = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int lectureId = Integer.parseInt(tokenizer.nextToken());
            lectures.add(new Lecture(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
        }

        lectures.sort((o1, o2) -> o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start);
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(lectures.get(0).end);
        for (int i = 1; i < N; i++) {
            if (!priorityQueue.isEmpty() && lectures.get(i).start >= priorityQueue.peek()) {
                priorityQueue.poll();
            }
            priorityQueue.add(lectures.get(i).end);
        }
        System.out.println(priorityQueue.size());
        bw.flush();
        bw.close();
        br.close();
    }
}