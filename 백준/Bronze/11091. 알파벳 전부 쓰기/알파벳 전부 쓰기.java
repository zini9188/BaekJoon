import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            boolean[] alpha = new boolean[26];
            int cnt = 0;
            String input = br.readLine();
            for (int j = 0; j < input.length(); j++) {
                char c = input.charAt(j);
                if (Character.isAlphabetic(c) && !alpha[Character.toLowerCase(c) - 'a']) {
                    alpha[Character.toLowerCase(c) - 'a'] = true;
                    cnt++;
                }
            }

            if (cnt == 26) {
                sb.append("pangram");
            } else {
                sb.append("missing ");
                for (int j = 0; j < 26; j++) {
                    if (!alpha[j]) {
                        sb.append((char) (j + 'a'));
                    }
                }
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}