import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int answer = 0;

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(tokenizer.nextToken());
        int P = Integer.parseInt(tokenizer.nextToken());

        String customDna = br.readLine();

        tokenizer = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(tokenizer.nextToken());
        int C = Integer.parseInt(tokenizer.nextToken());
        int G = Integer.parseInt(tokenizer.nextToken());
        int T = Integer.parseInt(tokenizer.nextToken());

        Map<Character, Integer> count = new HashMap<>();
        count.put('A', 0);
        count.put('C', 0);
        count.put('G', 0);
        count.put('T', 0);

        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < customDna.length(); i++) {
            char c = customDna.charAt(i);
            queue.add(c);
            
            if (queue.size() > P && count.containsKey(c)) {
                count.put(queue.peek(), count.get(queue.poll()) - 1);
            }

            if (count.containsKey(c)) {
                count.put(c, count.get(c) + 1);
            }
            
            if (queue.size() == P && count.get('A') >= A && count.get('C') >= C && count.get('G') >= G && count.get('T') >= T) {
                answer++;
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}