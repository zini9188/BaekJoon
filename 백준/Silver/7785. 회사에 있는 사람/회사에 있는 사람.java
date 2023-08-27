import java.io.*;
import java.util.Comparator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Map<String, String> log = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            String name = tokenizer.nextToken();
            String entrance = tokenizer.nextToken();
            log.put(name, entrance);
        }

        for (String s : log.keySet()) {
            if (log.get(s).equals("enter")) {
                sb.append(s).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}