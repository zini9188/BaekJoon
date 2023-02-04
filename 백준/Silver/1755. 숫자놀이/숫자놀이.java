import java.io.*;
import java.util.*;

public class Main {
    static class Pair {
        String word;
        int num;

        public Pair(String word, int num) {
            this.word = word;
            this.num = num;
        }
    }

    static String[] english = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
    static ArrayList<Pair> result;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());

        solution(N, M);
        output(bw);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution(int N, int M) {
        result = new ArrayList<>();
        for (int i = N; i <= M; i++) {
            result.add(new Pair(transEnglish(i), i));
        }
        result.sort(Comparator.comparing(o -> o.word));
    }

    static String transEnglish(int number) {
        return number >= 10 ? english[number / 10] + " " +
                english[number % 10] : english[number];
    }

    private static void output(BufferedWriter bw) throws IOException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < result.size(); i++) {
            builder.append(result.get(i).num).append(" ");
            if ((i + 1) % 10 == 0) {
                builder.append("\n");
            }
        }
        bw.write(builder.toString());
    }
}