import java.io.*;

public class Main {
    static StringBuilder result = new StringBuilder();

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
        int len = S.length();
        StringBuilder temp = new StringBuilder();
        while (index < len) {
            if (isMatch(S, index, ' ')) {
                result.append(S.charAt(index++));
            } else if (isMatch(S, index, '<')) {
                while (isUnMatch(S, index, '>')) {
                    result.append(S.charAt(index++));
                }
                result.append(S.charAt(index++));
            } else {
                while (index < len && isUnMatch(S, index, ' ') && isUnMatch(S, index, '<')) {
                    temp.append(S.charAt(index++));
                }
                for (int i = temp.length() - 1; i >= 0; i--) {
                    result.append(temp.charAt(i));
                }
                temp = new StringBuilder();
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