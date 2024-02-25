import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static Map<Long, Long> cards = new HashMap<>();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        long max = 0;
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(br.readLine());
            cards.put(num, cards.getOrDefault(num, 0L) + 1);
            max = Math.max(cards.get(num), max);
        }

        long ans = Long.MAX_VALUE;
        for (Long aLong : cards.keySet().stream().sorted().collect(Collectors.toList())) {
            if (cards.get(aLong) == max && ans > aLong) {
                ans = aLong;
            }
        }

        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}