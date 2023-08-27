import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();
    static int count;

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            String S = br.readLine();
            sb.append(isPalindrome(S)).append(" ").append(count).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int isPalindrome(String s) {
        count = 0;
        return recursion(s, 0, s.length() - 1);
    }

    private static int recursion(String s, int l, int r) {
        count++;
        if (l >= r) return 1;
        else if (s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l + 1, r - 1);
    }
}