import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static int N, K, ans, learn;
    private static int[] words;

    public static void main(String[] args) throws IOException {
        N = read();
        K = read();

        String str = "antic";
        for (int i = 0; i < str.length(); i++) {
            learn |= (1 << str.charAt(i) - 'a');
        }

        if (K < 5) {
            ans = 0;
        } else if (K == 26) {
            ans = N;
        } else {
            words = new int[N];
            for (int i = 0; i < N; i++) {
                String word = br.readLine();
                int bit = 0;
                for (char c : word.toCharArray()) {
                    bit |= (1 << c - 'a');
                }
                words[i] = bit;
            }
            dfs(0, 5);
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int s, int k) {
        if (k == K) {
            int read = 0;
            for (int word : words) {
                if ((word & learn) == word) {
                    read++;
                }
            }
            if (read > ans) {
                ans = read;
            }
            return;
        }

        for (int i = s; i < 26; i++) {
            if ((learn & (1 << i)) == 0) {
                learn |= (1 << i);
                dfs(i, k + 1);
                learn ^= (1 << i);
            }
        }
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }
        return n;
    }
}