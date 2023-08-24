import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String S = br.readLine();
        int i = Integer.parseInt(br.readLine());
        sb.append(S.charAt(i - 1));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
