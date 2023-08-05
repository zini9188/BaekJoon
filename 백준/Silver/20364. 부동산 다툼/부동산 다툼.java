import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        StringBuilder builder = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        int Q = Integer.parseInt(tokenizer.nextToken());
        visited = new int[N + 1];
        for (int i = 0; i < Q; i++) {
            int want = Integer.parseInt(br.readLine());
            if (visited[want] == 0) {
                builder.append(0).append('\n');
                fun(want, want);
            } else {
                builder.append(visited[want]).append('\n');
            }
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void fun(int ground, int start) {
        if (ground > N) {
            return;
        }
        visited[ground] = start;

        fun(ground * 2, start);
        fun(ground * 2 + 1, start);
    }
}