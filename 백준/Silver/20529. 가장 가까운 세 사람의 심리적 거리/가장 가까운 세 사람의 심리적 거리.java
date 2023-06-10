import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder answer = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            String[] mbti = new String[N];

            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                mbti[j] = tokenizer.nextToken();
            }

            if (N > 32) {
                answer.append("0\n");
                continue;
            }

            answer.append(findCase(mbti)).append("\n");
        }

        bw.write(answer.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static int findCase(String[] mbti) {
        int result = Integer.MAX_VALUE;
        for (int i = 0; i < mbti.length - 2; i++) {
            for (int j = i + 1; j < mbti.length - 1; j++) {
                for (int k = j + 1; k < mbti.length; k++) {
                    String first = mbti[i];
                    String second = mbti[j];
                    String third = mbti[k];

                    result = Math.min(result, getDistance(first, second) + getDistance(first, third) + getDistance(second, third));
                }
            }
        }
        return result;
    }

    private static int getDistance(String a, String b) {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                count++;
            }
        }
        return count;
    }
}