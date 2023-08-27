import java.io.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static Set<String> set = new LinkedHashSet<>();
    static String S;

    public static void main(String[] args) throws IOException {
        S = br.readLine();
        for (int i = 0; i < S.length(); i++) {
            dfs(i, String.valueOf(S.charAt(i)));
        }
        sb.append(set.size());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void dfs(int depth, String ans) {
        set.add(ans);
        if (depth == S.length() - 1) {
            return;
        }
        dfs(depth + 1, ans + S.charAt(depth + 1));
    }

}