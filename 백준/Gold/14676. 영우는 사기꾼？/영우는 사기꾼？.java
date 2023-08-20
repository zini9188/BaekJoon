import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        int[] possible = new int[N + 1];
        int[] built = new int[N + 1];
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(tokenizer.nextToken());
            int Y = Integer.parseInt(tokenizer.nextToken());
            graph.get(X).add(Y);
            possible[Y]++;
        }

        boolean isLier = false;
        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int operate = Integer.parseInt(tokenizer.nextToken());
            int a = Integer.parseInt(tokenizer.nextToken());

            if (operate == 1) {
                if (possible[a] > 0) {
                    isLier = true;
                    break;
                }

                if (++built[a] == 1) {
                    for (Integer next : graph.get(a)) {
                        possible[next]--;
                    }
                }
            } else {
                if (built[a] == 0) {
                    isLier = true;
                    break;
                }

                if (--built[a] == 0) {
                    for (Integer next : graph.get(a)) {
                        possible[next]++;
                    }
                }
            }
        }

        bw.write((isLier ? "Lier!" : "King-God-Emperor"));
        bw.close();
        br.close();
    }
}