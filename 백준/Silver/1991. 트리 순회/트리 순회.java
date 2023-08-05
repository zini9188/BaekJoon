import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        Node rootNode = new Node("A", null, null);

        int n = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer;
        for (int i = 0; i < n; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            String node = tokenizer.nextToken();
            String left = tokenizer.nextToken();
            String right = tokenizer.nextToken();
            addNode(rootNode, node, left, right);
        }

        prefix(rootNode);
        System.out.println();
        infix(rootNode);
        System.out.println();
        postfix(rootNode);
        bw.flush();
        bw.close();
        br.close();
    }

    private static void addNode(Node node, String value, String left, String right) {
        if (node.value.equals(value)) {
            node.left = left.equals(".") ? null : new Node(left, null, null);
            node.right = right.equals(".") ? null : new Node(right, null, null);
        } else {
            if (node.left != null) addNode(node.left, value, left, right);
            if (node.right != null) addNode(node.right, value, left, right);
        }
    }

    private static void prefix(Node node) {
        if (node == null) {
            return;
        }
        System.out.print(node.value);
        prefix(node.left);
        prefix(node.right);
    }

    private static void infix(Node node) {
        if (node == null) {
            return;
        }
        infix(node.left);
        System.out.print(node.value);
        infix(node.right);
    }

    private static void postfix(Node node){
        if(node == null){
            return;
        }
        postfix(node.left);
        postfix(node.right);
        System.out.print(node.value);
    }

    static class Node {
        String value;
        Node left, right;

        public Node(String value, Node left, Node right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}