import java.io.*;
import java.util.StringTokenizer;

class GameBoard {
    private final int field, target;
    private int current;
    private final boolean[] trap;

    public GameBoard(int field, int target) {
        this.field = field;
        this.target = target;
        trap = new boolean[field + 1];
    }

    public void addTrap(int n) {
        trap[n] = true;
    }

    public boolean moveField(int n) {
        current = 1;
        boolean[] visited = trap.clone();
        while (current != target) {
            move(n);
            if (visited[current]) {
                return false;
            }
            visited[current] = true;
        }
        return true;
    }

    private void move(int n) {
        current += n;
        if (current > field) {
            current -= field;
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int Z = Integer.parseInt(tokenizer.nextToken());
        int M = Integer.parseInt(tokenizer.nextToken());
        GameBoard gameBoard = new GameBoard(N, Z);
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            gameBoard.addTrap(Integer.parseInt(tokenizer.nextToken()));
        }
        for (int i = 1; i < N; i++) {
            if (gameBoard.moveField(i)) {
                bw.write(i + "");
                break;
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}