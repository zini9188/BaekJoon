import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[][] ingredients;
    static boolean[] visited;
    static int N;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        ingredients = new int[N][2];
        visited = new boolean[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(tokenizer.nextToken());
            int B = Integer.parseInt(tokenizer.nextToken());
            ingredients[i][0] = S;
            ingredients[i][1] = B;
        }

        makeFood(0, 1, 0);

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void makeFood(int depth, int sour, int salty) {
        if (depth >= 1) {
            answer = Math.min(Math.abs(sour - salty), answer);
        }
        if (depth == N) {
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                makeFood(depth + 1, sour * ingredients[i][0], salty + ingredients[i][1]);
                visited[i] = false;
            }
        }
    }
}