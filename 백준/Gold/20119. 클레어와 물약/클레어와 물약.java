import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        InDegree[] inDegree = new InDegree[N + 1];
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            inDegree[i] = new InDegree();
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int[] inDegreeIndex = new int[N + 1];
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(tokenizer.nextToken());
            List<Integer> list = new LinkedList<>();
            for (int j = 0; j < K; j++) {
                int need = Integer.parseInt(tokenizer.nextToken());
                list.add(need);
            }
            int r = Integer.parseInt(tokenizer.nextToken());
            for (int vertex : list) {
                inDegree[r].plus(inDegreeIndex[r]);
                graph.get(vertex).add(new Node(r, inDegreeIndex[r]));
            }
            inDegreeIndex[r]++;
        }

        int L = Integer.parseInt(br.readLine());
        boolean[] created = new boolean[N + 1];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < L; i++) {
            int position = Integer.parseInt(tokenizer.nextToken());
            queue.add(position);
        }

        PriorityQueue<Integer> ans = new PriorityQueue<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            if (created[cur]) {
                continue;
            }

            ans.add(cur);
            created[cur] = true;
            for (Node node : graph.get(cur)) {
                if (inDegree[node.value].minus(node.index) == 0) {
                    queue.add(node.value);
                }
            }
        }

        sb.append(ans.size()).append("\n");
        while (!ans.isEmpty()) {
            sb.append(ans.poll()).append(" ");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    static class Node {
        int value;
        int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }

    static class InDegree {
        HashMap<Integer, Integer> inDegree = new HashMap<>();

        void plus(int key) {
            inDegree.put(key, inDegree.getOrDefault(key, 0) + 1);
        }

        int minus(int key) {
            inDegree.put(key, inDegree.getOrDefault(key, 0) - 1);
            return inDegree.get(key);
        }
    }
}