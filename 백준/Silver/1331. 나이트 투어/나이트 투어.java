import java.io.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        boolean[][] board = new boolean[7][7];
        Point[] points = new Point[36];
        boolean flag = false;
        for (int i = 0; i < 36; i++) {
            String input = br.readLine();
            int x = input.charAt(0) - 'A' + 1;
            int y = input.charAt(1) - '0';
            points[i] = new Point(x, y);
            if (board[x][y]) {
                flag = true;
            }
            board[x][y] = true;
        }

        if (!flag) {
            for (int i = 1; i < 35; i++) {
                int curX = points[i].x;
                int curY = points[i].y;
                int befX = points[i - 1].x;
                int befY = points[i - 1].y;
                if (Math.abs(curX - befX) == 2 && Math.abs(curY - befY) == 1 || Math.abs(curX - befX) == 1 && Math.abs(curY - befY) == 2) {
                } else {
                    flag = true;
                    break;
                }
            }
            
            int lastX = points[35].x;
            int lastY = points[35].y;
            int startX = points[0].x;
            int startY = points[0].y;
            if (Math.abs(startX - lastX) == 2 && Math.abs(lastY - startY) == 1 || Math.abs(startX - lastX) == 1 && Math.abs(lastY - startY) == 2) {
            } else {
                flag = true;
            }
        }
        
        bw.write(flag ? "Invalid" : "Valid");
        bw.flush();
        bw.close();
        br.close();
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}