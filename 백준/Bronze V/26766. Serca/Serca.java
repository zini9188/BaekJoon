import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            System.out.println(" @@@   @@@ \n"
                    + "@   @ @   @\n"
                    + "@    @    @\n"
                    + "@         @\n"
                    + " @       @ \n"
                    + "  @     @  \n"
                    + "   @   @   \n"
                    + "    @ @    \n"
                    + "     @    ");
        }
        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}