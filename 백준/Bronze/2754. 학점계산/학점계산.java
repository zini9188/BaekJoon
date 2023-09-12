import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        double[] grade = {4.3, 4.0, 3.7, 3.3, 3.0, 2.7, 2.3, 2.0, 1.7, 1.3, 1.0, 0.7, 0.0};
        Map<String, Integer> map =  new HashMap<>() {{
            put("A+", 0);
            put("A0", 1);
            put("A-", 2);
            put("B+", 3);
            put("B0", 4);
            put("B-", 5);
            put("C+", 6);
            put("C0", 7);
            put("C-", 8);
            put("D+", 9);
            put("D0", 10);
            put("D-", 11);
            put("F", 12);
        }};
        String g = br.readLine();
        sb.append(grade[map.get(g)]);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}