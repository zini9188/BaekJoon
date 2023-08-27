import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(tokenizer.nextToken());
        int B = Integer.parseInt(tokenizer.nextToken());

        Map<Integer, Boolean> map = new HashMap<>();
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < A; i++) {
            map.put(Integer.valueOf(tokenizer.nextToken()), true);
        }

        int same = 0;
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < B; i++) {
            int el = Integer.parseInt(tokenizer.nextToken());
            if (map.containsKey(el)) {
                same++;
            }
        }
        sb.append(A + B - 2 * same);        
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}