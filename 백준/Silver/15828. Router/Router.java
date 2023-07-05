import java.io.*;
import java.util.LinkedList;
import java.util.Queue;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Queue<Integer> queue = new LinkedList<>();
        while (true) {
            int n = Integer.parseInt(br.readLine());
            if (n == -1) {
                break;
            }

            if (n == 0) {
                queue.poll();
                continue;
            }

            if (queue.size() < N) {
                queue.add(n);
            }
        }

        StringBuilder builder = new StringBuilder();
        if(queue.isEmpty()){
            builder.append("empty");
        }else{
            while (!queue.isEmpty()) {
                builder.append(queue.poll()).append(" ");
            }
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}