import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder builder = new StringBuilder();
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int giantCount = Integer.parseInt(tokenizer.nextToken());
        int centi = Integer.parseInt(tokenizer.nextToken());
        int limitHammer = Integer.parseInt(tokenizer.nextToken());
        PriorityQueue<Integer> heights = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < giantCount; i++) {
            heights.add(Integer.valueOf(br.readLine()));
        }
        int cnt = 0;
        while (!heights.isEmpty() && centi <= heights.peek() && limitHammer > 0) {
            if (heights.peek() == 1) {
                cnt++;
                break;
            } else {
                heights.add(heights.poll() / 2);
                limitHammer--;
                cnt++;
            }
        }
        if(centi > heights.peek()){
            builder.append("YES").append("\n").append(cnt);
        }else{
            builder.append("NO").append("\n").append(heights.peek());
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}