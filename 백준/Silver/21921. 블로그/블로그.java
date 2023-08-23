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
        int X = Integer.parseInt(tokenizer.nextToken());

        int[] visit = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            visit[i] = Integer.parseInt(tokenizer.nextToken());
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int visitor = 0;
        int max = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            queue.add(visit[i]);
            if (queue.size() < X) {
                visitor += visit[i];
            } else if (queue.size() == X) {
                visitor += visit[i];
                if (max < visitor) {
                    count = 0;
                    max = visitor;
                }
                if(visitor == max){
                    count++;
                }

                visitor -= queue.isEmpty() ? 0 : queue.poll();
            }
        }
        
        if(max == 0){
            sb.append("SAD");
        }else{
            sb.append(max).append("\n").append(count);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
