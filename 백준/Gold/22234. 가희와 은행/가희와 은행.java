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

        Queue<Customer> waiting = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(tokenizer.nextToken());
            int t = Integer.parseInt(tokenizer.nextToken());
            waiting.add(new Customer(P, t));
        }

        int M = Integer.parseInt(br.readLine());
        PriorityQueue<Customer> entranceCustomer = new PriorityQueue<>(Comparator.comparingInt(o -> o.entranceTime));
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int P = Integer.parseInt(tokenizer.nextToken());
            int t = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            entranceCustomer.add(new Customer(P, t, c));
        }

        int time = 0;
        Customer currentCustomer = null;
        List<Integer> answers = new ArrayList<>();
        while (time < W) {
            if (!waiting.isEmpty()) {
                currentCustomer = waiting.poll();
                if (currentCustomer.taskTime > T) {
                    for (int i = 0; i < T; i++) {
                        answers.add(currentCustomer.customerId);
                    }
                    currentCustomer.taskTime -= T;
                    time += T;
                } else {
                    for (int i = 0; i < currentCustomer.taskTime; i++) {
                        answers.add(currentCustomer.customerId);
                    }
                    time += currentCustomer.taskTime;
                    currentCustomer.taskTime = 0;
                }
            }

            while (!entranceCustomer.isEmpty() && entranceCustomer.peek().entranceTime <= time) {
                waiting.add(entranceCustomer.poll());
            }

            if (currentCustomer != null) {
                if (currentCustomer.taskTime > 0) {
                    waiting.add(new Customer(currentCustomer.customerId, currentCustomer.taskTime));
                }
                currentCustomer = null;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < W && i < answers.size(); i++) {
            builder.append(answers.get(i)).append("\n");
        }

        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static class Customer {
        int customerId;
        int taskTime;
        int entranceTime;

        public Customer(int customerId, int taskTime) {
            this.customerId = customerId;
            this.taskTime = taskTime;
        }

        public Customer(int customerId, int taskTime, int entranceTime) {
            this.customerId = customerId;
            this.taskTime = taskTime;
            this.entranceTime = entranceTime;
        }
    }
}