import java.io.*;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());
        Stack<Person> stack = new Stack<>();

        long pair = 0;
        for (int i = 0; i < N; i++) {
            int height = Integer.parseInt(br.readLine());
            Person person = new Person(1, height);
            while (!stack.isEmpty() && stack.peek().height <= height) {
                Person pop = stack.pop();
                if (pop.height == height) {
                    person.same += pop.same;
                }
                pair += pop.same;
            }

            if (!stack.isEmpty()) {
                pair++;
            }

            stack.add(person);
        }


        sb.append(pair);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Person {
        int same;
        int height;

        public Person(int same, int height) {
            this.same = same;
            this.height = height;
        }
    }
}