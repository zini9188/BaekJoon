import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        int[] survey = new int[N];
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            survey[i] = Integer.parseInt(input.substring(input.length() - 3));
        }

        int expect;
        int participants = 1;
        for (int can = 0; can < N; participants++) {
            can = 0;
            for (int score : survey) {
                for (expect = 0; expect <= participants && expect * 1000 / participants <= score; expect++) {
                    if (expect * 1000 / participants == score) {
                        can++;
                        break;
                    }
                }
                if (expect * 1000 / participants > score) {
                    break;
                }
            }
        }

        sb.append(participants - 1);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}