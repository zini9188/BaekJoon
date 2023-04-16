import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, -1, 1, 1, 1, -1, -1};
    static int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
    static Map<String, Integer> dir = new HashMap<>() {{
        put("R", 0);
        put("L", 1);
        put("B", 2);
        put("T", 3);
        put("RT", 4);
        put("LT", 5);
        put("RB", 6);
        put("LB", 7);
    }};

    static class Piece {
        int x, y;

        public Piece(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void move(int dir, Piece piece) {
            int nx = x + dx[dir];
            int ny = y + dy[dir];
            int npx = piece.x + dx[dir];
            int npy = piece.y + dy[dir];
            if (nx == piece.x && ny == piece.y) {
                if (isInRange(npx, npy)) {
                    x = nx;
                    y = ny;
                    piece.x = npx;
                    piece.y = npy;
                }
            } else if (isInRange(nx, ny)) {
                x = nx;
                y = ny;
            }
        }

        private boolean isInRange(int x, int y) {
            return x >= 0 && y >= 0 && x < 8 && y < 8;
        }

        @Override
        public String toString() {
            return String.format("%c%d", (char) (y + 'A'), x + 1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        String kingPos = tokenizer.nextToken();
        Piece king = new Piece(kingPos.charAt(1) - '1', kingPos.charAt(0) - 'A');
        String stonePos = tokenizer.nextToken();
        Piece stone = new Piece(stonePos.charAt(1) - '1', stonePos.charAt(0) - 'A');
        int N = Integer.parseInt(tokenizer.nextToken());

        for (int i = 0; i < N; i++) {
            String cmd = br.readLine();
            int nd = dir.get(cmd);
            king.move(nd, stone);
        }
        bw.write(king + "\n");
        bw.write(stone.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}