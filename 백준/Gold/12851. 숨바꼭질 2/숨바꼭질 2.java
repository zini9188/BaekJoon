import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int MAX_VALUE = 100001;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        boolean[] visited = new boolean[MAX_VALUE];
        Queue<Person> queue = new LinkedList<>();
        queue.add(new Person(N, 0));
        int answer = MAX_VALUE;
        int count = 0;
        while (!queue.isEmpty()) {
            Person p = queue.poll();
            int X = p.pos;
            int time = p.time;
            visited[X] = true;
            
            if (X == K) {
                if (answer > time) {
                    answer = time;
                    count = 1;
                } else if (answer == time) {
                    count++;
                }
            }

            if (X + 1 < MAX_VALUE && !visited[X + 1]) {
                queue.add(new Person(X + 1, time + 1));
            }
            if (X - 1 >= 0 && !visited[X - 1]) {
                queue.add(new Person(X - 1, time + 1));
            }
            if (X * 2 < MAX_VALUE && !visited[X * 2]) {
                queue.add(new Person(X * 2, time + 1));
            }
        }

        bw.write(answer + "\n" + count);
        bw.flush();
        bw.close();
        br.close();
    }

    static class Person {
        int pos, time;

        public Person(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }
}