import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int T = Integer.parseInt(tokenizer.nextToken());
        int W = Integer.parseInt(tokenizer.nextToken());

        Queue<Customer> waitingLine = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(tokenizer.nextToken());
            int t = Integer.parseInt(tokenizer.nextToken());
            waitingLine.offer(new Customer(p, t));
        }

        int M = Integer.parseInt(br.readLine());
        PriorityQueue<Customer> admissionCustomer = new PriorityQueue<>(Comparator.comparingInt(o -> o.admissionTime));
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(tokenizer.nextToken());
            int t = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            admissionCustomer.offer(new Customer(p, t, c));
        }

        StringBuilder sb = new StringBuilder();
        Customer currentCustomer = null;
        for (int time = 0; time < W; ) {
            if (!waitingLine.isEmpty()) {
                currentCustomer = waitingLine.poll();

                int minTime = Math.min(currentCustomer.taskTime, T);
                for (int i = 0; i < minTime && time < W; i++) {
                    sb.append(currentCustomer.id).append("\n");
                    time++;
                }
                currentCustomer.taskTime -= minTime;
            }

            while (!admissionCustomer.isEmpty() && admissionCustomer.peek().admissionTime <= time) {
                waitingLine.offer(admissionCustomer.poll());
            }

            if (currentCustomer != null && currentCustomer.taskTime > 0) {
                waitingLine.offer(new Customer(currentCustomer.id, currentCustomer.taskTime));
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Customer {
        int id;
        int taskTime;
        int admissionTime;

        public Customer(int id, int taskTime) {
            this.id = id;
            this.taskTime = taskTime;
        }

        public Customer(int id, int taskTime, int admissionTime) {
            this.id = id;
            this.taskTime = taskTime;
            this.admissionTime = admissionTime;
        }
    }
}