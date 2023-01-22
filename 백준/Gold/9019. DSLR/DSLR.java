import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        String cmd;
        int changed;

        public Pair(String cmd, int changed) {
            this.cmd = cmd;
            this.changed = changed;
        }
    }

    static int T;
    static int A, B;
    static Queue<Pair> queue;
    static StringBuilder builder;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        T = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer;
        builder = new StringBuilder();
        for (int i = 0; i < T; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            A = Integer.parseInt(tokenizer.nextToken());
            B = Integer.parseInt(tokenizer.nextToken());
            visited = new boolean[10001];
            solution();
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        queue = new PriorityQueue<>((Comparator.comparingInt(o -> o.cmd.length())));
        queue.add(new Pair("", A));
        builder.append(bfs()).append("\n");
    }

    private static String bfs() {
        while (!queue.isEmpty()) {
            Pair temp = queue.poll();
            String command = temp.cmd;
            int target = temp.changed;
            if (target == B) {
                return command;
            }
            int D = operateD(target);
            int S = operateS(target);
            int L = operateL(target);
            int R = operateR(target);

            if(!visited[D]){
                visited[D] = true;
                queue.add(new Pair(command + "D", D));
            }
            if(!visited[S]){
                visited[S] = true;
                queue.add(new Pair(command + "S", S));
            }
            if(!visited[L]){
                visited[L] = true;
                queue.add(new Pair(command + "L", L));
            }
            if(!visited[R]){
                visited[R] = true;
                queue.add(new Pair(command + "R", R));
            }
        }
        return null;
    }

    private static int operateD(int a) {
        return (a * 2) % 10000;
    }

    private static int operateS(int a) {
        return a == 0 ? 9999 : a - 1;
    }

    private static int operateL(int a) {
        return (a % 1000 * 10) + a / 1000;
    }

    private static int operateR(int a) {
        return (a % 10 * 1000) + a / 10;
    }
}