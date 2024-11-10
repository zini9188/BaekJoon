import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static Egg[] eggs;
    private static int N, ans = 0;

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());
        eggs = new Egg[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs[i] = new Egg(s, w);
        }

        dfs(0, 0);

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static void dfs(int index, int broken) {
        if (index == N || broken == N - 1) {
            ans = Math.max(ans, broken);
            return;
        }

        if (eggs[index].S <= 0) {
            dfs(index + 1, broken);
            return;
        }


        for (int i = 0; i < N; i++) {
            if (eggs[i].S <= 0 || index == i) {
                continue;
            }

            eggs[i].S -= eggs[index].W;
            eggs[index].S -= eggs[i].W;

            int cnt = 0;
            if (eggs[i].S <= 0) {
                cnt++;
            }
            if (eggs[index].S <= 0) {
                cnt++;
            }
            dfs(index + 1, broken + cnt);
            eggs[i].S += eggs[index].W;
            eggs[index].S += eggs[i].W;
        }
    }

    public static class Egg {

        int S, W;

        public Egg(int s, int w) {
            S = s;
            W = w;
        }
    }
}