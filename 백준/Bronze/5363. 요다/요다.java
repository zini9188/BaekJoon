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
            String[] line = br.readLine().split(" ");
            int size = line.length;
            for (int j = 2; j < size; j++) {
                sb.append(line[j]).append(" ");
            }

            for (int j = 0; j < 2; j++) {
                sb.append(line[j]).append(" ");
            }

            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}