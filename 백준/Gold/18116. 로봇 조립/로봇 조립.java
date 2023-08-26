import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] count;
    static int[] parent;
    static final int MAX = 1000001;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        count = new int[MAX];
        parent = new int[MAX];
        for (int i = 0; i < MAX; i++) {
            parent[i] = i;
            count[i] = 1;
        }

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            String cmd = tokenizer.nextToken();
            if (cmd.equals("I")) {
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                union(a, b);
            } else {
                int c = Integer.parseInt(tokenizer.nextToken());
                sb.append(count[parent[find(c)]]).append("\n");
            }
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
        count[a] += count[b];
        count[b] = 0;
        parent[b] = a;
    }
}