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
            for (int i = 0; i < input.length(); i++) {
                char c = input.charAt(i);
                if (c == 'i') {
                    sb.append('e');
                } else if (c == 'e') {
                    sb.append('i');
                } else if (c == 'I') {
                    sb.append('E');
                } else if (c == 'E') {
                    sb.append('I');
                } else {
                    sb.append(c);
                }
            }
            sb.append("\n");
        }
        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}