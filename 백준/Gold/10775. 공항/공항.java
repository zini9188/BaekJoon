import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static int[] parent;

    public static void main(String[] args) throws IOException {
        int G = Integer.parseInt(br.readLine());

        parent = new int[G + 1];
        for (int i = 0; i <= G; i++) {
            parent[i] = i;
        }

        int P = Integer.parseInt(br.readLine());
        int count = 0;
        boolean isOpen = true;
        for (int i = 0; i < P; i++) {
            int gi = Integer.parseInt(br.readLine());
            if (!isOpen) {
                continue;
            }
            if (union(gi)) {
                count++;
            } else {
                isOpen = false;
            }
        }

        sb.append(count);
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

    static boolean union(int a) {
        a = find(a);
        if (a == 0) {
            return false;
        }
        parent[a] = find(a - 1);
        return true;
    }
}