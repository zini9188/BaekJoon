import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static final int SINGLE = 1;
    static final int DOUBLE = 2;
    static final int TRIPLE = 3;
    static final int HOMERUN = 4;
    static boolean[] visited;
    static int N;
    static int[] players;
    static Team team;
    static Player[] player;
    static int ans;

    public static void main(String[] args) throws IOException {
        init();
        findPlayer(2);
        sb.append(ans);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        player = new Player[10];
        for (int i = 1; i < 10; i++) {
            player[i] = new Player();
        }

        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            for (int p = 1; p < 10; p++) {
                player[p].score.add(Integer.valueOf(tokenizer.nextToken()));
            }
        }

        players = new int[10];
        visited = new boolean[10];
        visited[4] = true;
        players[4] = 1;
    }

    private static void findPlayer(int number) {
        if (number == 10) {
            playGame();
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (!visited[i]) {
                player[number].sequence = i;
                players[i] = number;
                visited[i] = true;
                findPlayer(number + 1);
                player[number].sequence = 0;
                players[i] = 0;
                visited[i] = false;
            }
        }
    }

    private static void playGame() {
        team = new Team(N);
        int total = 0;
        while (!team.gameOver()) {
            if (team.isTripleOut()) {
                team.nextInning();
            }
            int currentPlayer = players[team.player];
            int hit = player[currentPlayer].score.get(team.currentInning - 1);
            int score = team.move(hit);
            if (score == -1) {
                team.out++;
            } else {
                total += score;
            }
            team.nextPlayer();
        }
        ans = Math.max(total, ans);
    }

    static class Team {
        boolean first, second, third;
        int maxInning;
        int currentInning = 1;
        int out;
        int player = 1;

        public Team(int maxInning) {
            this.maxInning = maxInning;
        }

        public void init() {
            out = 0;
            first = false;
            second = false;
            third = false;
        }

        boolean gameOver() {
            return currentInning == maxInning && isTripleOut();
        }

        boolean isTripleOut() {
            return out == 3;
        }

        void nextPlayer() {
            team.player = team.player == 9 ? 1 : ++team.player;
        }

        void nextInning() {
            currentInning++;
            init();
        }

        int move(int hit) {
            if (hit == HOMERUN) {
                int count = (third ? 1 : 0) + (second ? 1 : 0) + (first ? 1 : 0) + 1;
                third = false;
                second = false;
                first = false;
                return count;
            }
            if (hit == TRIPLE) {
                int count = (third ? 1 : 0) + (second ? 1 : 0) + (first ? 1 : 0);
                third = true;
                second = false;
                first = false;
                return count;
            }
            if (hit == DOUBLE) {
                int count = (third ? 1 : 0) + (second ? 1 : 0);
                third = first;
                second = true;
                first = false;
                return count;
            }
            if (hit == SINGLE) {
                int count = (third ? 1 : 0);
                third = second;
                second = first;
                first = true;
                return count;
            }
            return -1;
        }
    }

    static class Player {
        ArrayList<Integer> score = new ArrayList<>();
        int sequence;
    }
}
