import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] parent;
    static int[] A;
    static int[] count;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        parent = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parent[i] = i;
        }

        A = new int[N + 1];
        count = new int[N + 1];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            A[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Arrays.fill(count, 1);
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(tokenizer.nextToken());
            int w = Integer.parseInt(tokenizer.nextToken());
            union(v, w);
        }

        int ans = 0;
        boolean[] visited = new boolean[N + 1];
        for (int i = 1; i <= N; i++) {
            int root = find(i);
            if (visited[root]) {
                continue;
            }
            visited[root] = true;
            visited[i] = true;
            ans += A[root];
        }

        if (ans > K) {
            sb.append("Oh no");
        } else {
            sb.append(ans);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    static void union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return;
        }
        if (A[a] > A[b]) {
            count[b] += count[a];
            count[a] = 0;
            parent[a] = b;
        } else {
            count[a] += count[b];
            count[b] = 0;
            parent[b] = a;
        }
    }
}