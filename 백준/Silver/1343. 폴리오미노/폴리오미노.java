import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String line = br.readLine();
        line = line.replaceAll("XXXX", "AAAA");
        line = line.replaceAll("XX", "BB");

        if (line.contains("X")) {
            sb.append("-1");
        } else {
            sb.append(line);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}