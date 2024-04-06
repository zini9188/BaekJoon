import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String line = br.readLine();

        if (error(line)) {
            sb.append("Error!");
        } else {
            for (int i = 0; i < line.length(); i++) {
                if (Character.isUpperCase(line.charAt(i))) {
                    sb.append("_").append(Character.toLowerCase(line.charAt(i)));
                } else if (line.charAt(i) == '_') {
                    sb.append(Character.toUpperCase(line.charAt((i++) + 1)));
                } else {
                    sb.append(line.charAt(i));
                }
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static boolean error(String line) {
        if (line.charAt(0) == '_' || line.charAt(line.length() - 1) == '_') {
            return true;
        }

        if (Character.isUpperCase(line.charAt(0))) {
            return true;
        }

        boolean upper = false;
        boolean under = false;
        for (int i = 0; i < line.length(); i++) {
            if (Character.isUpperCase(line.charAt(i))) {
                upper = true;
            }

            if (line.charAt(i) == '_') {
                under = true;

                if (i > 1 && line.charAt(i - 1) == '_') {
                    return true;
                }
            }
        }

        return upper && under;
    }
}