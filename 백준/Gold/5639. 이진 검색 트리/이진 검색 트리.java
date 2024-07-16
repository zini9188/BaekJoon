import java.io.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String input;
        Node node = new Node(Integer.parseInt(br.readLine()));
        while ((input = br.readLine()) != null) {
            node.insert(Integer.parseInt(input));
        }

        search(node);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static void search(Node node) {
        if (node == null) {
            return;
        }

        search(node.left);
        search(node.right);
        sb.append(node.num).append("\n");
    }

    private static class Node {

        int num;
        Node left, right;

        public Node(int num) {
            this.num = num;
        }

        void insert(int num) {
            if (num > this.num) {
                if (right == null) {
                    right = new Node(num);
                } else {
                    right.insert(num);
                }
            } else {
                if (left == null) {
                    left = new Node(num);
                } else {
                    left.insert(num);
                }
            }
        }
    }
}