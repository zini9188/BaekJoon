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
            waitingLine.add(new Customer(p, t));
        }

        int M = Integer.parseInt(br.readLine());
        PriorityQueue<Customer> admissionCustomer = new PriorityQueue<>(Comparator.comparingInt(o -> o.admissionTime));
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(tokenizer.nextToken());
            int t = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            admissionCustomer.add(new Customer(p, t, c));
        }

        int time = 0;
        Customer currentCustomer = null;
        StringBuilder builder = new StringBuilder();
        while (time < W) {
            if (!waitingLine.isEmpty()) {
                currentCustomer = waitingLine.poll();
                if (currentCustomer.taskTime > T) {
                    for (int i = 0; i < T && time < W; i++) {
                        builder.append(currentCustomer.id).append("\n");
                        time++;
                        currentCustomer.taskTime--;
                    }
                } else {
                    for (int i = 0; i < currentCustomer.taskTime && time < W; i++) {
                        builder.append(currentCustomer.id).append("\n");
                        time++;
                    }
                    currentCustomer.taskTime = 0;
                }
            }

            while (!admissionCustomer.isEmpty() && admissionCustomer.peek().admissionTime <= time) {
                waitingLine.add(admissionCustomer.poll());
            }

            if (currentCustomer != null) {
                if (currentCustomer.taskTime > 0) {
                    waitingLine.add(new Customer(currentCustomer.id, currentCustomer.taskTime));
                }
                currentCustomer = null;
            }
        }

        bw.write(builder.toString());
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