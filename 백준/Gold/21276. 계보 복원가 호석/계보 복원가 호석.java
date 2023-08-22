import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        ArrayList<String> peoples = new ArrayList<>();
        boolean[][] graph = new boolean[N][N];
        ArrayList<PriorityQueue<Integer>> child = new ArrayList<>();
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            peoples.add(tokenizer.nextToken());
            child.add(new PriorityQueue<>());
        }
        Collections.sort(peoples);

        Map<String, Integer> sequence = new HashMap<>();
        for (int i = 0; i < peoples.size(); i++) {
            sequence.put(peoples.get(i), i);
        }

        int[] inDegree = new int[N];
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int des = sequence.get(tokenizer.nextToken());
            int anc = sequence.get(tokenizer.nextToken());
            inDegree[des]++;
            graph[anc][des] = true;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        ArrayList<Integer> ancestor = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                ancestor.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for(int next = 0; next < N; next++){
                if (graph[cur][next] && --inDegree[next] == 0) {
                    queue.add(next);
                    child.get(cur).add(next);
                }
            }
        }

        sb.append(ancestor.size()).append("\n");
        for (int anc : ancestor) {
            sb.append(peoples.get(anc)).append(" ");
        }
        sb.append("\n");

        for (int i = 0; i < N; i++) {
            sb.append(peoples.get(i)).append(" ");
            sb.append(child.get(i).size()).append(" ");
            PriorityQueue<Integer> pq = child.get(i);
            while (!pq.isEmpty()) {
                sb.append(peoples.get(pq.poll())).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}