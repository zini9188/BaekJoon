import java.io.*;

public class Main {
    static String str, target;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        str = br.readLine();
        target = br.readLine();
        bw.write(solution());
        bw.flush();
        bw.close();
        br.close();
    }

    private static String solution() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            builder.append(str.charAt(i));
            if (builder.length() >= target.length()) {
                explosionStr(builder);
            }
        }
        return builder.length() == 0 ? "FRULA" : builder.toString();
    }

    private static StringBuilder explosionStr(StringBuilder builder) {
        if (builder.substring(builder.length() - target.length(), builder.length()).equals(target)) {
            builder.delete(builder.length() - target.length(), builder.length());
        }
        return builder;
    }
}