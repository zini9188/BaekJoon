import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        double totalCredit = 0;
        double totalGrade = 0;
        for (int i = 0; i < 20; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            tokenizer.nextToken();
            double credit = Double.parseDouble(tokenizer.nextToken());
            String grade = tokenizer.nextToken();
            if(grade.equals("P")){
                continue;
            }
            totalCredit += credit;
            totalGrade += getGrade(grade) * credit;
        }
        sb.append(String.format("%.6f",totalGrade / totalCredit));
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static double getGrade(String grade) {
        if (grade.equals("A+")) {
            return 4.5;
        }
        if (grade.equals("A0")) {
            return 4.0;
        }
        if (grade.equals("B+")) {
            return 3.5;
        }
        if (grade.equals("B0")) {
            return 3.0;
        }
        if (grade.equals("C+")) {
            return 2.5;
        }
        if (grade.equals("C0")) {
            return 2.0;
        }
        if (grade.equals("D+")) {
            return 1.5;
        }
        if (grade.equals("D0")) {
            return 1.0;
        }
        return 0.0;
    }
}
