import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static final String AEIOU = "aeiou";
    static ArrayList<String> answer = new ArrayList<>();
    static int L, C;
    static String[] guess;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());

        L = Integer.parseInt(tokenizer.nextToken());
        C = Integer.parseInt(tokenizer.nextToken());

        guess = new String[C];
        visited = new boolean[C];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            guess[i] = tokenizer.nextToken();
        }
        Arrays.sort(guess);

        dfs(0, 0, 0, "");

        StringBuilder builder = new StringBuilder();
        for (String s : answer) {
            builder.append(s).append("\n");
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static void dfs(int index, int consonant, int vowel, String password) {
        if (isPossiblePassword(consonant, vowel)) {
            answer.add(password);
            return;
        }

        for (int i = index; i < C; i++) {
            if (!visited[i]) {
                visited[i] = true;
                String nextPassword = password + guess[i];
                if (AEIOU.contains(guess[i])) {
                    dfs(i + 1, consonant, vowel + 1, nextPassword);
                } else {
                    dfs(i + 1, consonant + 1, vowel, nextPassword);
                }
                visited[i] = false;
            }
        }
    }

    private static boolean isPossiblePassword(int consonant, int vowel) {
        return consonant + vowel == L && vowel >= 1 && consonant >= 2;
    }
}