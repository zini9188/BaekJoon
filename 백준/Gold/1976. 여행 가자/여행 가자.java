import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] parents;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        parents = new int[N + 1];
        for (int i = 0; i < N + 1; i++) {
            parents[i] = i;
        }

        for (int i = 1; i <= N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                if (Integer.parseInt(tokenizer.nextToken()) == 1) {
                    union(i, j);
                }
            }
        }

        sb.append(findPath(M));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String findPath(int M) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int parent = find(Integer.parseInt(tokenizer.nextToken()));
        for (int i = 1; i < M; i++) {
            int path = find(Integer.parseInt(tokenizer.nextToken()));
            if (path != parent) {
                return "NO";
            }
        }
        return "YES";
    }

    public static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    public static void union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return;
        }
        parents[a] = b;
    }
}