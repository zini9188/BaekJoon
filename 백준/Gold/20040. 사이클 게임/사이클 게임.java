import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] parents;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        int answer = 0;
        for (int i = 1; i <= M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            int b = Integer.parseInt(tokenizer.nextToken());
            if (union(a, b)) {
                answer = i;
                break;
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean union(int a, int b) {
        int x1 = find(a);
        int x2 = find(b);
        if (x1 == x2) {
            return true;
        }
        parents[x2] = x1;
        return false;
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }
}