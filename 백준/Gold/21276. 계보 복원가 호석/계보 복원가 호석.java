import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        Queue<String> pq = new PriorityQueue<>();
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            pq.add(tokenizer.nextToken());
        }

        Map<String, Integer> sequence = new HashMap<>();
        String[] name = new String[N];
        int index = 0;
        while (!pq.isEmpty()) {
            name[index] = pq.peek();
            sequence.put(pq.poll(), index++);
        }

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegree = new int[N];
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int des = sequence.get(tokenizer.nextToken());
            int anc = sequence.get(tokenizer.nextToken());
            inDegree[des]++;
            graph.get(anc).add(des);
        }

        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<String> ancestor = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                ancestor.add(name[i]);
            }
        }

        Collections.sort(ancestor);
        sb.append(ancestor.size()).append("\n");
        for (String anc : ancestor) {
            sb.append(anc).append(" ");
        }
        sb.append("\n");

        Map<String, PriorityQueue<String>> child = new HashMap<>();
        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Integer next : graph.get(cur)) {
                if (--inDegree[next] == 0) {
                    queue.add(next);
                    PriorityQueue<String> l = child.getOrDefault(name[cur], new PriorityQueue<>());
                    l.add(name[next]);
                    child.put(name[cur], l);
                }
            }
        }

        for (String n : name) {
            sb.append(n).append(" ").append(child.getOrDefault(n, new PriorityQueue<>()).size()).append(" ");
            for (String c : child.getOrDefault(n, new PriorityQueue<>())) {
                sb.append(c).append(" ");
            }
            sb.append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}