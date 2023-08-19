import java.io.*;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        int[] possible = new int[N + 1];

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            graph.get(A).add(B);
            possible[B]++;
        }

        boolean[] visited = new boolean[N + 1];
        Queue<Subject> queue = new ArrayDeque<>();
        for (int i = 1; i < N + 1; i++) {
            if(possible[i] == 0) {
                queue.add(new Subject(1, i));
            }
        }

        while (!queue.isEmpty()) {
            Subject current = queue.poll();

            if (possible[current.num] > 0 || visited[current.num]) {
                continue;
            }
            visited[current.num] = true;
            possible[current.num] = current.semester;

            for (Integer next : graph.get(current.num)) {
                if (--possible[next] == 0) {
                    queue.add(new Subject(current.semester + 1, next));
                }
            }
        }

        for (int i = 1; i < N + 1; i++) {
           sb.append(possible[i]).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Subject {
        int semester;
        int num;

        public Subject(int semester, int num) {
            this.semester = semester;
            this.num = num;
        }
    }
}