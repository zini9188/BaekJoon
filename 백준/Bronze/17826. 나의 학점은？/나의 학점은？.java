import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        List<Integer> grade = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 50; i++) {
            grade.add(Integer.valueOf(st.nextToken()));
        }

        int hongik = Integer.parseInt(br.readLine());
        for (int i = 0; i < grade.size(); i++) {
            if (hongik == grade.get(i)) {
                int j = i + 1;
                if (j <= 5) {
                    sb.append("A+");
                } else if (j <= 15) {
                    sb.append("A0");
                } else if (j <= 30) {
                    sb.append("B+");
                } else if (j <= 35) {
                    sb.append("B0");
                } else if (j <= 45) {
                    sb.append("C+");
                } else if (j <= 48) {
                    sb.append("C0");
                } else {
                    sb.append("F");
                }
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}