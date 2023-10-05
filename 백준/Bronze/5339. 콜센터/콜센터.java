import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        sb.append("     /~\\\n" +
                "    ( oo|\n" +
                "    _\\=/_\n" +
                "   /  _  \\\n" +
                "  //|/.\\|\\\\\n" +
                " ||  \\ /  ||\n" +
                "============\n" +
                "|          |\n" +
                "|          |\n" +
                "|          |");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}