import java.io.*;

public class Main {
    static String result = "";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String S = br.readLine();
        solution(S);
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution(String S) {
        int index = 0;
        String temp = "";
        while (index < S.length()) {
            if (isMatch(S, index, ' ')) {
                result += S.charAt(index++);
            } else if (isMatch(S, index, '<')) {
                while (isUnMatch(S, index, '>')) {
                    result += S.charAt(index++);
                }
                result += S.charAt(index++);
            } else {
                while (index < S.length() && isUnMatch(S, index, ' ') && isUnMatch(S, index, '<')) {
                    temp += S.charAt(index++);
                }
                for (int i = temp.length() - 1; i >= 0; i--) {
                    result += temp.charAt(i);
                }
                temp = "";
            }
        }
    }

    private static boolean isUnMatch(String S, int index, char x) {
        return S.charAt(index) != x;
    }

    private static boolean isMatch(String S, int index, char x) {
        return S.charAt(index) == x;
    }
}