import java.io.*;
import java.util.*;

public class Main {
    static class Course {
        private int start, end;

        public Course(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer;
        int N = Integer.parseInt(br.readLine());
        List<Course> courses = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(tokenizer.nextToken());
            int T = Integer.parseInt(tokenizer.nextToken());
            courses.add(new Course(S, T));
        }
        courses.sort((o1, o2) -> o1.start == o2.start ? o1.end - o2.end : o1.start - o2.start);
        Queue<Integer> queue = new PriorityQueue<>();
        queue.offer(courses.get(0).end);
        for (int i = 1; i < N; i++) {
            if (!queue.isEmpty() && queue.peek() <= courses.get(i).start) {
                queue.poll();
            }
            queue.offer(courses.get(i).end);
        }
        bw.write(queue.size() + "");
        bw.flush();
        bw.close();
        br.close();
    }
}