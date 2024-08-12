import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static int N, K, ans, learn;
    private static String[] words;
    private static List<Character> alphabet;

    public static void main(String[] args) throws IOException {
        N = read();
        K = read();

        String str = "antic";
        for (int i = 0; i < str.length(); i++) {
            learn |= (1 << str.charAt(i) - 'a');
        }

        alphabet = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            if ((learn & (1 << i)) == 0) {
                alphabet.add((char) (i + 'a'));
            }
        }

        words = new String[N];
        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        dfs(0, 5);
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int s, int k) {
        if (k == K) {
            int read = 0;
            for (int i = 0; i < N; i++) {
                read++;
                for (char c : words[i].toCharArray()) {
                    if ((learn & (1 << c - 'a')) == 0) {
                        read--;
                        break;
                    }
                }
            }
            ans = Math.max(ans, read);
            return;
        }

        for (int i = s; i < alphabet.size(); i++) {
            int c = alphabet.get(i) - 'a';
            if ((learn & (1 << c)) == 0) {
                learn |= (1 << c);
                dfs(i, k + 1);
                learn ^= (1 << c);
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