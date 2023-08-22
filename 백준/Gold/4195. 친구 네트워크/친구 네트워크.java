import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] parent;
    static int[] count;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int testCase = 1; testCase <= T; testCase++) {
            int F = Integer.parseInt(br.readLine());
            parent = new int[F * 2];
            count = new int[F * 2];
            Map<String, Integer> names = new HashMap<>();
            int index = 0;

            for (int i = 0; i < F * 2; i++) {
                parent[i] = i;
            }

            for (int i = 0; i < F; i++) {
                tokenizer = new StringTokenizer(br.readLine());
                String a = tokenizer.nextToken();
                String b = tokenizer.nextToken();
                if (!names.containsKey(a)) {
                    names.put(a, index);
                    count[index++] = 1;
                }
                if (!names.containsKey(b)) {
                    names.put(b, index);
                    count[index++] = 1;
                }

                int n = union(names.get(a), names.get(b));
                sb.append(n).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static int union(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) {
            return count[a];
        }
        if (a < b) {
            parent[b] = a;
            count[a] += count[b];
            return count[a];
        }
        parent[a] = b;
        count[b] += count[a];
        return count[b];
    }

    static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }
}