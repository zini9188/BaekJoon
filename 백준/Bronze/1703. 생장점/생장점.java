import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {

        StringBuilder builder = new StringBuilder();
        while (true) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(tokenizer.nextToken());
            if (a == 0) {
                break;
            }
            int answer = 1;
            for (int i = 0; i < a; i++) {
                int splittingFactor = Integer.parseInt(tokenizer.nextToken());
                int cut = Integer.parseInt(tokenizer.nextToken());
                answer *= splittingFactor;
                answer -= cut;
            }
            builder.append(answer).append("\n");
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}