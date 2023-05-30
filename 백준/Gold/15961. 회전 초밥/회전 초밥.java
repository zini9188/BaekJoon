import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int d = Integer.parseInt(tokenizer.nextToken());
        int k = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());

        int[] sushi = new int[2 * N];
        for (int i = 0; i < N; i++) {
            int kind = Integer.parseInt(br.readLine());
            sushi[i] = kind;
            sushi[i + N] = kind;
        }

        int left = 0, right = 0;
        int temp = 1;
        int answer = 0;
        Map<Integer, Integer> counts = new HashMap<>();
        int eat = 1;
        counts.put(sushi[0], 1);
        while (left <= right && right < N * 2 - 1) {
            if (eat < k) {
                right++;
                eat++;
                int find = counts.getOrDefault(sushi[right], 0);
                if (find + 1 == 1) {
                    temp++;
                }
                counts.put(sushi[right], find + 1);
            } else {
                int find = counts.getOrDefault(sushi[left], 0);
                if (find - 1 == 0) {
                    temp--;
                }
                counts.put(sushi[left], find - 1);
                left++;
                eat--;
            }
            if (eat == k && counts.getOrDefault(c, 0) == 0) {
                answer = Math.max(answer, temp + 1);
            } else {
                answer = Math.max(answer, temp); 
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }
}