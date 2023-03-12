import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int totalDurability = 0;
    static int N, K;
    static Belt[] beltInfo;
    static int loadPosition, unloadPosition;
    static Queue<Integer> robotPositions;
    static int answer = 0;

    static class Belt {
        int durability;
        boolean isLoad;

        public Belt(int durability, boolean isLoad) {
            this.durability = durability;
            this.isLoad = isLoad;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        N = Integer.parseInt(tokenizer.nextToken());
        K = Integer.parseInt(tokenizer.nextToken());
        tokenizer = new StringTokenizer(br.readLine());
        beltInfo = new Belt[N * 2 + 1];
        robotPositions = new LinkedList<>();
        for (int i = 1; i <= 2 * N; i++) {
            beltInfo[i] = new Belt(Integer.parseInt(tokenizer.nextToken()), false);
        }
        solution();
        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static void solution() {
        loadPosition = 1;
        unloadPosition = N;
        while (totalDurability < K) {
            moveBelt();
            moveRobot();
            loadRobot();
            answer++;
        }
    }

    private static void moveBelt() {
        loadPosition = --loadPosition == 0 ? 2 * N : loadPosition;
        unloadPosition = --unloadPosition == 0 ? 2 * N : unloadPosition;
    }

    private static void moveRobot() {
        int size = robotPositions.size();
        for (int i = 0; i < size; i++) {
            int currentPosition = robotPositions.poll();
            Belt currentBelt = beltInfo[currentPosition];
            currentBelt.isLoad = false;

            if (currentPosition == unloadPosition) continue;

            int next = currentPosition + 1;
            if (next > 2 * N) next = 1;

            Belt nextBelt = beltInfo[next];
            if (nextBelt.durability >= 1 && !nextBelt.isLoad) {
                nextBelt.durability--;
                if (nextBelt.durability == 0) totalDurability++;
                if (next == unloadPosition) continue;
                nextBelt.isLoad = true;
                robotPositions.add(next);
            } else {
                robotPositions.add(currentPosition);
                currentBelt.isLoad = true;
            }
        }
    }

    private static void loadRobot() {
        Belt belt = beltInfo[loadPosition];
        if (belt.durability >= 1 && !belt.isLoad) {
            belt.durability--;
            belt.isLoad = true;
            robotPositions.add(loadPosition);

            if (belt.durability == 0) totalDurability++;
        }
    }
}