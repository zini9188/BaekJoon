import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[] A;
    public static boolean[] visited;
    public static int[] temp;
    public static int res = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        visited = new boolean[N];
        temp = new int[N];        
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++)
            A[i] = Integer.parseInt(tokenizer.nextToken());

        dfs(0);
        bw.write(String.valueOf(res));
        bw.flush();
        bw.close();
        br.close();
    }

    public static void dfs(int cnt) {
        if (cnt == N) {
            res = Math.max(res, calcSum());
            return;
        }
        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp[cnt] = A[i];
                dfs(cnt + 1);
                visited[i] = false;
            }
        }
    }

    private static int calcSum() {
        int sum = 0;
        for (int i = 0; i < N - 1; i++) {
            sum += Math.abs(temp[i] - temp[i + 1]);
        }
        return sum;
    }
}
