import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static class Queue {
        int[] elements;
        int rear;
        int front;

        public Queue() {
            elements = new int[10001];
            rear = 0;
            front = 0;
        }

        public void push(int x) {
            elements[rear++] = x;
        }

        public void pop() {
            if (rear != front) {
                System.out.println(elements[front++]);
            } else System.out.println("-1");
        }

        public void size() {
            System.out.println(rear - front);
        }

        public void isEmpty() {
            if (rear == front) System.out.println("1");
            else System.out.println("0");
        }

        public void front() {
            if (rear != front) System.out.println(elements[front]);
            else System.out.println("-1");
        }

        public void back() {
            if (rear != front) System.out.println(elements[rear - 1]);
            else System.out.println("-1");
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Queue queue = new Queue();

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            String command = tokenizer.nextToken();
            switch (command) {
                case "push":
                    int x = Integer.parseInt(tokenizer.nextToken());
                    queue.push(x);
                    break;
                case "pop":
                    queue.pop();
                    break;
                case "size":
                    queue.size();
                    break;
                case "empty":
                    queue.isEmpty();
                    break;
                case "front":
                    queue.front();
                    break;
                case "back":
                    queue.back();
                    break;
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}