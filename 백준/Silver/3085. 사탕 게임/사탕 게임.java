import java.io.*;

public class Main {
    static int n;
    static char[][] board;
    static int[] dx = {1, 0,};
    static int[] dy = {0, 1,};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        board = new char[n][n];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }
        bw.write(solution() + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static int solution() {
        int result = 0;
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                for (int k = 0; k < 2; k++) {
                    int nx = x + dx[k];
                    int ny = y + dy[k];
                    if (!outOfRange(nx, ny) && !isSame(nx, ny, x, y)) {
                        swap(nx, ny, x, y);
                        result = Math.max(findCandy(), result);
                        swap(nx, ny, x, y);
                    }
                    if (result == n) {
                        return result;
                    }
                }
            }
        }
        return result;
    }

    private static int findCandy() {
        int result = 0;
        for (int i = 0; i < n; i++) {
            int rowCnt = 1;
            int colCnt = 1;
            for (int j = 0; j < n - 1; j++) {
                if (board[i][j] == board[i][j + 1]) {
                    rowCnt++;
                } else {
                    rowCnt = 1;
                }

                if (board[j][i] == board[j + 1][i]) {
                    colCnt++;
                } else {
                    colCnt = 1;
                }
                result = Math.max(Math.max(colCnt, rowCnt), result);
            }
        }
        return result;
    }


    private static void swap(int nx, int ny, int x, int y) {
        char temp = board[x][y];
        board[x][y] = board[nx][ny];
        board[nx][ny] = temp;
    }

    private static boolean outOfRange(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= n || ny >= n;
    }

    private static boolean isSame(int nx, int ny, int x, int y) {
        return board[nx][ny] == board[x][y];
    }
}