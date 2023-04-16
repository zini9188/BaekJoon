import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static class Exam implements Comparable<Exam> {
        String name;
        int kor, eng, math;

        public Exam(String name, int kor, int eng, int math) {
            this.name = name;
            this.kor = kor;
            this.eng = eng;
            this.math = math;
        }

        @Override
        public int compareTo(Exam other) {
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

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Exam> exams = new PriorityQueue<>(Exam::compareTo);
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            exams.add(new Exam(tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken())));
        }
        StringBuilder builder = new StringBuilder();
        while (!exams.isEmpty()) {
            builder.append(exams.poll().name).append("\n");
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}