import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        sb.append(A* B);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}