import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;
    private static char[] str, temp;
    private static int len;
    private static Set<String> set;
    private static int[] visited;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            str = br.readLine().toCharArray();
            len = str.length;
            temp = new char[len];
            set = new HashSet<>();
            visited = new int[26];
            for (char c : str) {
                visited[c - 'a']++;
            }

            dfs(0);

            List<String> answer = new ArrayList<>(set);
            Collections.sort(answer);
            for (String s : answer) {
                sb.append(s).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int depth) {
        if (depth == len) {
            set.add(new String(temp));
            return;
        }

        for (int i = 0; i < 26; i++) {
            if (visited[i] > 0) {
                visited[i]--;
                temp[depth] = (char) (i + 'a');
                dfs(depth + 1);
                temp[depth] = ' ';
                visited[i]++;
            }
        }
    }
}