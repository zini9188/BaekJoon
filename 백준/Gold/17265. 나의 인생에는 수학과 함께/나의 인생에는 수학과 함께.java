import java.io.*;
import java.util.*;

public class Main {

    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static final StringBuilder sb = new StringBuilder();
    private static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        int N = read();
        char[][] board = new char[N][N];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();

            for (int j = 0; j < input.length(); j += 2) {
                board[i][j / 2] = input.charAt(j);
            }
        }

        Queue<Node> q = new ArrayDeque<>();
        q.add(new Node(0, 0, board[0][0] - '0', ' '));

        int[] dx = {1, 0}, dy = {0, 1};

        Result res = new Result();
        while (!q.isEmpty()) {
            Node node = q.poll();

            if (node.x == N - 1 && node.y == N - 1) {
                res.comp(node.num);
                continue;
            }

            for (int i = 0; i < 2; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];
                int nn = node.num;
                char ns = node.sign;

                if (nx >= N || ny >= N) {
                    continue;
                }

                if ((nx + ny) % 2 == 0) {
                    if (ns == ' ') {
                        break;
                    }

                    nn = calc(nn, board[nx][ny] - '0', ns);
                } else {
                    ns = board[nx][ny];
                }

                q.add(new Node(nx, ny, nn, ns));
            }
        }

        sb.append(res.max).append(" ").append(res.min);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int calc(int a, int b, char sign) {
        if (sign == '+') {
            return a + b;
        } else if (sign == '-') {
            return a - b;
        }
        return a * b;
    }

    private static int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean negative = n == 13;
        if (negative) {
            n = 0;
        }

        while ((c = System.in.read()) > 32) {
            n = (n << 3) + (n << 1) + (c & 15);
        }

        return negative ? -n : n;
    }

    private static class Result {

        int max, min;

        public Result() {
            max = Integer.MIN_VALUE;
            min = Integer.MAX_VALUE;
        }

        public void comp(int num) {
            max = Math.max(max, num);
            min = Math.min(min, num);
        }
    }

    private static class Node {

        int x, y, num;
        char sign;

        public Node(int x, int y, int num, char sign) {
            this.x = x;
            this.y = y;
            this.num = num;
            this.sign = sign;
        }
    }
}