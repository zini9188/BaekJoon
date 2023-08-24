import java.io.*;

public class Main {

    static final int MAX = 100000 * 100 + 1;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int init = 2;
        for (int i = 0; i < N; i++) {
            init += init - 1;
        }

        sb.append((int) Math.pow(init, 2));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}