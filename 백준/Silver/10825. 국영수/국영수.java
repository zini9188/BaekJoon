import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Exam implements Comparable {
    String name;
    int kor, eng, math;

    public Exam(String name, int kor, int eng, int math) {
        this.name = name;
        this.kor = kor;
        this.eng = eng;
        this.math = math;
    }

    @Override
    public int compareTo(Object o) {
        Exam other = (Exam) o;
        if (this.kor == other.kor) {
            if (this.eng == other.eng) {
                if (this.math == other.math) {
                    return name.compareTo(other.name);
                }
                return other.math - this.math;
            }
            return this.eng - other.eng;
        }
        return other.kor - this.kor;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Exam> exams = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            exams.add(new Exam(tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
        }
        exams.sort(Exam::compareTo);
        exams.forEach(exam -> System.out.println(exam.name));
        bw.flush();
        bw.close();
        br.close();
    }
}