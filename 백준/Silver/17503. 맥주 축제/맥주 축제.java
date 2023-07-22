import java.io.*;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int N, M, K;

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        M = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());

        ArrayList<Beer> beers = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(tokenizer.nextToken());
            int c = Integer.parseInt(tokenizer.nextToken());
            beers.add(new Beer(v, c));
        }
        beers.sort((o1, o2) -> o1.level == o2.level ? o2.favor - o1.favor : o1.level - o2.level);

        int favor = 0;
        int minLevel = -1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (Beer beer : beers) {
            favor += beer.favor;
            pq.add(beer.favor);

            if (pq.size() > N) {
                favor -= pq.poll();
            }

            if (pq.size() == N && favor >= M) {
                minLevel = beer.level;
                break;
            }
        }

        bw.write(minLevel + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static class Beer {
        int favor;
        int level;

        public Beer(int favor, int level) {
            this.favor = favor;
            this.level = level;
        }
    }
}