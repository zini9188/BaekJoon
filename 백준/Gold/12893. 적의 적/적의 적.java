import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int[] parent;
    static int[] enemy;
    static int N, M;

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        parent = new int[N + 1];
        enemy = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }

        boolean flag = false;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int a = find(A);
            int b = find(B);

            if (a == b) {
                flag = true;
            }

            int aE = enemy[a];
            int bE = enemy[b];

            if (aE != 0) {
                union(aE, b);
            } else {
                enemy[a] = b;
            }

            if (bE != 0) {
                union(bE, a);
            } else {
                enemy[b] = a;
            }
        }

        sb.append(flag ? "0" : "1");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int find(int x) {
        if (parent[x] == x) {
            return x;
        }
        return parent[x] = find(parent[x]);
    }

    private static boolean union(int a, int b) {
        a = find(a);
        b = find(b);

        if (a == b) {
            return true;
        }
        parent[a] = b;
        return false;
    }
}