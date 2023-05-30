import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        Map<Integer, Integer> cards = new HashMap<>();
        for (int i = 0; i < n; i++) {
            cards.put(Integer.valueOf(tokenizer.nextToken()), 1);
        }

        int m = Integer.parseInt(br.readLine());
        tokenizer = new StringTokenizer(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < m; i++) {
            int find = Integer.parseInt(tokenizer.nextToken());
            builder.append(cards.containsKey(find) ? 1 : 0).append(" ");
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}