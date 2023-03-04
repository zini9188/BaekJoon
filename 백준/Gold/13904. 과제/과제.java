import java.io.*;
import java.util.*;

public class Main {
    static class Homework {
        int deadline;
        int score;

        public Homework(int deadline, int score) {
            this.deadline = deadline;
            this.score = score;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Homework> lectures = new ArrayList<>();
        int[] scores = new int[1001];
        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            lectures.add(new Homework(Integer.parseInt(tokenizer.nextToken()) - 1, Integer.parseInt(tokenizer.nextToken())));
        }
        lectures.sort((o1, o2) -> o2.score - o1.score);
        for (int i = 0; i < N; i++) {
            int deadline = lectures.get(i).deadline;
            int score = lectures.get(i).score;
            if (scores[deadline] == 0) {
                scores[deadline] = score;
            } else {
                while (deadline > 0 && scores[deadline] != 0) {
                    deadline--;
                }
                scores[deadline] = Math.max(score, scores[deadline]);
            }
        }
        int result = 0;
        for (int score : scores) {
            result += score;
        }

        bw.write(result + "");
        bw.flush();
        bw.close();
        br.close();
    }
}