import java.io.*;
import java.util.StringTokenizer;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static char[] chars;
    private static String word;

    public static void main(String[] args) throws IOException {
        word = br.readLine();
        chars = new char[word.length()];
        dfs(0, word.length() - 1);

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int start, int end) {
        if (start > end) {
            return;
        }

        int idx = start;
        for (int i = start; i <= end; i++) {
            if (word.charAt(idx) > word.charAt(i)) {
                idx = i;
            }
        }
        chars[idx] = word.charAt(idx);

        for (char c : chars) {
            if (c != 0) {
                sb.append(c);
            }
        }
        sb.append("\n");

        dfs(idx + 1, end);
        dfs(start, idx - 1);
    }
}