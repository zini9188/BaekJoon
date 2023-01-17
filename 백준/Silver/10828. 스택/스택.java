import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static class Stack {
        int size;
        int[] elements;
        int index;

        public Stack(int size) {
            this.size = size;
            elements = new int[size];
            index = 0;
        }

        public void push(int x) {
            if (index < size) {
                elements[index++] = x;
            }
        }

        public void pop() {
            if (index >= 1 && index <= size) {
                System.out.println(elements[--index]);
            }
            else System.out.println("-1");
        }

        public void size() {
            System.out.println(index);
        }

        public void isEmpty() {
            if(index == 0) System.out.println("1");
            else System.out.println("0");
        }

        public void top() {
            if (index >= 1 && index <= size) {
                System.out.println(elements[index - 1]);
            }
            else System.out.println("-1");
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        Stack stack = new Stack(N);

        for (int i = 0; i < N; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            String command = tokenizer.nextToken();
            switch (command) {
                case "push":
                    int x = Integer.parseInt(tokenizer.nextToken());
                    stack.push(x);
                    break;
                case "pop":
                    stack.pop();
                    break;
                case "size":
                    stack.size();
                    break;
                case "empty":
                    stack.isEmpty();
                    break;
                case "top":
                    stack.top();
                    break;
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}