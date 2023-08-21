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
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        ArrayList<PriorityQueue<Integer>> child = new ArrayList<>();
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            peoples.add(tokenizer.nextToken());
            child.add(new PriorityQueue<>());
            graph.add(new ArrayList<>());
        }
        Collections.sort(peoples);

        Map<String, Integer> sequence = new HashMap<>();
        String[] sortedName = new String[N];
        int index = 0;
        for (String people : peoples) {
            sortedName[index] = people;
            sequence.put(people, index++);
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
        ArrayList<Integer> ancestor = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
                ancestor.add(i);
            }
        }

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            for (Integer next : graph.get(cur)) {
                if (--inDegree[next] == 0) {
                    queue.add(next);
                    child.get(cur).add(next);
                }
            }
        }

        sb.append(ancestor.size()).append("\n");
        for (int anc : ancestor) {
            sb.append(sortedName[anc]).append(" ");
        }
        sb.append("\n");

        for (int i = 0; i < N; i++) {
            sb.append(sortedName[i]).append(" ");
            sb.append(child.get(i).size()).append(" ");
            PriorityQueue<Integer> pq = child.get(i);
            while (!pq.isEmpty()) {
                sb.append(sortedName[pq.poll()]).append(" ");
            }
            sb.append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}