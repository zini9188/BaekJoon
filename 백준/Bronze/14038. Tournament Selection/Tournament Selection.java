import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int win = 0;
        for (int i = 0; i < 6; i++) {
            String flag = br.readLine();
            if (flag.equals("W")) {
                win++;
            }
        }

        if (win >= 5) {
            sb.append("1");
        } else if (win >= 3) {
            sb.append("2");
        } else if (win >= 1) {
            sb.append("3");
        } else {
            sb.append("-1");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}