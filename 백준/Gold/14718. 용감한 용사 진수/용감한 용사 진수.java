import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = read();
        int K = read();
        Ability[] abilities = new Ability[N];
        for (int i = 0; i < N; i++) {
            abilities[i] = new Ability(read(), read(), read());
        }

        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    int x = abilities[i].s;
                    int y = abilities[j].d;
                    int z = abilities[k].i;

                    int cnt = 0;
                    for (int l = 0; l < N; l++) {
                        if (x >= abilities[l].s && y >= abilities[l].d && z >= abilities[l].i) {
                            cnt++;
                        }
                    }

                    if (cnt == K) {
                        ans = Math.min(ans, x + y + z);
                    }
                }
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }

    private static class Ability {

        int s, d, i;

        public Ability(int s, int d, int i) {
            this.s = s;
            this.d = d;
            this.i = i;
        }
    }
}