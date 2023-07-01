import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int K = Integer.parseInt(tokenizer.nextToken());

        PriorityQueue<Jewel> jewels = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(tokenizer.nextToken());
            int price = Integer.parseInt(tokenizer.nextToken());
            jewels.add(new Jewel(weight, price));
        }

        PriorityQueue<Jewel> bags = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));
        for (int i = 0; i < K; i++) {
            int weight = Integer.parseInt(br.readLine());
            bags.add(new Jewel(weight, 0));
        }

        long answer = 0;
        PriorityQueue<Jewel> inHand = new PriorityQueue<>((o1, o2) -> o2.price - o1.price);
        while (!bags.isEmpty()) {
            Jewel bag = bags.poll();

            while (!jewels.isEmpty() && jewels.peek().weight <= bag.weight) {
                Jewel jewel = jewels.poll();
                inHand.add(jewel);
            }

            if (!inHand.isEmpty()) {
                answer += inHand.poll().price;
            }
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static class Jewel {
        int weight, price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }
    }
}