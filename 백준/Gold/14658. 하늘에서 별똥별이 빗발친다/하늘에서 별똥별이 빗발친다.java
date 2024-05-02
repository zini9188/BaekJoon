import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        List<Star> stars = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            stars.add(new Star(x, y));
        }

        int ans = 0;
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                Trampauline t = new Trampauline(stars.get(i).x, stars.get(j).y, L);
                int cnt = 0;
                for (int k = 0; k < K; k++) {
                    if (t.has(stars.get(k))) {
                        cnt++;
                    }
                }

                if (cnt > ans) {
                    ans = cnt;
                }
            }
        }

        sb.append(K - ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Star {

        int x, y;

        public Star(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Trampauline {

        int x1, y1, x2, y2;

        public Trampauline(int x1, int y1, int L) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x1 + L;
            this.y2 = y1 + L;
        }

        public boolean has(Star s) {
            return s.x >= x1 && s.x <= x2 && s.y >= y1 && s.y <= y2;
        }
    }
}