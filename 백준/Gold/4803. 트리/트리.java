import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int tc = 1;
        while (true) {
            tokenizer = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(tokenizer.nextToken());
            int m = Integer.parseInt(tokenizer.nextToken());

            if (n == 0 && m == 0) {
                break;
            }

            parent = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < m; i++) {
                tokenizer = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(tokenizer.nextToken());
                int b = Integer.parseInt(tokenizer.nextToken());
                union(a, b);
            }

            Set<Integer> set = new HashSet<>();
            for (int i = 1; i <= n; i++) {
                int find = find(parent[i]);
                if (find > 0) {
                    set.add(find);
                }
            }

            int size = set.size();
            sb.append("Case ").append(tc).append(": ");
            if (size == 0) {
                sb.append("No trees.");
            } else if (size == 1) {
                sb.append("There is one tree.");
            } else {
                sb.append("A forest of ").append(size).append(" trees.");
            }
            sb.append("\n");
            tc++;
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
            parent[a] = 0;
            return;
        }

        if (a > b) {
            parent[a] = b;
        } else {
            parent[b] = a;
        }
    }
}