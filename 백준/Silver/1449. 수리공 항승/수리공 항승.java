import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int L = Integer.parseInt(tokenizer.nextToken());

        List<Integer> pipes = new ArrayList<>();
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pipes.add(Integer.valueOf(tokenizer.nextToken()));
        }
        pipes.sort(Comparator.comparingInt(o -> o));

        int cnt = 0;
        int idx = 0;
        while (idx < N) {
            double end = pipes.get(idx) - 0.5 + L;
            while (idx < N && pipes.get(idx) <= end) {
                idx++;
            }
            cnt++;
        }

        sb.append(cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}