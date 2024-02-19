import static java.util.Arrays.sort;

import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Bracket> brackets = new ArrayList<>();
    static final char OPEN = '(', CLOSE = ')';
    static int size;
    static boolean[] visited;
    static int[] temp;
    static Set<String> cases = new HashSet<>();
    private static String line;

    public static void main(String[] args) throws IOException {
        line = br.readLine();

        Stack<Integer> indexQueue = new Stack<>();
        for (int idx = 0; idx < line.length(); idx++) {
            char curChar = line.charAt(idx);

            if (curChar == OPEN) {
                indexQueue.add(idx);
            } else if (curChar == CLOSE) {
                if (!indexQueue.isEmpty()) {
                    brackets.add(new Bracket(indexQueue.pop(), idx));
                }
            }
        }

        size = brackets.size();
        for (int i = 1; i <= size; i++) {
            visited = new boolean[i];
            temp = new int[i];
            dfs(i, 0, 0);
        }

        String[] answer = cases.toArray(new String[0]);
        sort(answer);
        for (String s : answer) {
            sb.append(s).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(int limit, int depth, int idx) {
        if (depth == limit) {
            boolean[] can = new boolean[line.length()];
            for (int i = 0; i < limit; i++) {
                Bracket bracket = brackets.get(temp[i]);
                can[bracket.left] = true;
                can[bracket.right] = true;
            }

            StringBuilder result = new StringBuilder();
            for (int i = 0; i < line.length(); i++) {
                if (!can[i]) {
                    result.append(line.charAt(i));
                }
            }
            cases.add(result.toString());
            return;
        }

        for (int i = idx; i < size; i++) {
            if (!visited[depth]) {
                visited[depth] = true;
                temp[depth] = i;
                dfs(limit, depth + 1, i + 1);
                visited[depth] = false;
                temp[depth] = 0;
            }
        }
    }

    static class Bracket {

        int left, right;

        public Bracket(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}