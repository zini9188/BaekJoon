import java.io.*;

public class Main {
    static String S;
    static StringBuilder result = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        S = br.readLine();
        solution();
        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        int index = 0;
        int len = S.length();
        StringBuilder temp = new StringBuilder();
        while (index < len) {
            if (isMatch(S.charAt(index), ' ')) {
                result.append(S.charAt(index++));
            } else if (isMatch(S.charAt(index), '<')) {
                index = appendToRightAngle(index);
            } else {
                while (index < len && !isMatch(S.charAt(index), ' ') && !isMatch(S.charAt(index), '<')) {
                    temp.append(S.charAt(index++));
                }
                result.append(temp.reverse());
                temp = new StringBuilder();
            }
        }
    }

    private static int appendToRightAngle(int index) {
        while (!isMatch(S.charAt(index), '>')) {
            result.append(S.charAt(index++));
        }
        result.append(S.charAt(index++));
        return index;
    }

    private static boolean isMatch(char target, char symbol) {
        return target == symbol;
    }
}