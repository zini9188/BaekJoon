import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String line = br.readLine();
        String pattern = "(100+1+|01)+";
        sb.append(line.matches(pattern) ? "SUBMARINE" : "NOISE");
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}