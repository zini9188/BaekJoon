import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String pwd = br.readLine();
            int[] count = new int[27];
            int max = 0;
            char answer = ' ';
            for (int j = 0; j < pwd.length(); j++) {
                if (pwd.charAt(j) == ' ') {
                    continue;
                }
                int c = pwd.charAt(j) - 'a';
                count[c]++;
                if (count[c] > max) {
                    answer = pwd.charAt(j);
                    max = count[c];
                } else if (count[c] == max) {
                    answer = '?';
                }
            }
            sb.append(answer).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}