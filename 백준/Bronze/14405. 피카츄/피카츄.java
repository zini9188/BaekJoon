import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String S = br.readLine();
        sb.append(canSpeak(S) ? "YES" : "NO");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean canSpeak(String S) {
        for (int i = 0; i < S.length(); ) {
            char c = S.charAt(i);
            if (c == 'p') {
                if (i + 1 >= S.length() || S.charAt(i + 1) != 'i') {
                    return false;
                }
                i += 2;
            } else if (c == 'k') {
                if (i + 1 >= S.length() || S.charAt(i + 1) != 'a') {
                    return false;
                }
                i += 2;
            } else if (c == 'c') {
                if (i + 2 >= S.length() || !(S.charAt(i + 1) == 'h' && S.charAt(i + 2) == 'u')) {
                    return false;
                }
                i += 3;
            } else {
                return false;
            }
        }
        return true;
    }
}