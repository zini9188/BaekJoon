import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String input;

        while ((input = br.readLine()) != null) {
            st = new StringTokenizer(input);
            String s = st.nextToken();
            String t = st.nextToken();
            sb.append(comp(s, t)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static String comp(String a, String b) {
        int i = 0;
        int j = 0;

        while (i < a.length() && j < b.length()) {
            char c1 = a.charAt(i);
            char c2 = b.charAt(j);

            if (c1 == c2) {
                i++;
            }
            j++;
        }

        if (i == a.length()) {
            return "Yes";
        }
        return "No";
    }
}