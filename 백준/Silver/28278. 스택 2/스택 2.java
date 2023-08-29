import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Stack stack = new Stack(N);

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int cmd = Integer.parseInt(tokenizer.nextToken());
            if (cmd == 1) {
                stack.add(Integer.parseInt(tokenizer.nextToken()));
            } else if (cmd == 2) {
                sb.append(stack.pop()).append("\n");
            } else if (cmd == 3) {
                sb.append(stack.size()).append("\n");
            } else if (cmd == 4) {
                sb.append(stack.isEmpty() ? 1 : 0).append("\n");
            } else {
                sb.append(stack.peek()).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Stack {
        private final int[] arr;
        private final int size;
        private int index = 0;

        public Stack(int size) {
            this.size = size;
            this.arr = new int[this.size];
        }

        public int size() {
            return index;
        }

        public boolean isEmpty() {
            return index == 0;
        }

        public boolean isFull() {
            return index == size - 1;
        }

        public void add(int x) {
            if (isFull()) {
                return;
            }
            arr[index++] = x;
        }

        public int pop() {
            if (isEmpty()) {
                return -1;
            }
            return arr[--index];
        }

        public int peek() {
            if (isEmpty()) {
                return -1;
            }
            return arr[index - 1];
        }
    }
}