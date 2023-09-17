import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static Line[] lines;
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        lines = new Line[2];
        for (int i = 0; i < 2; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(tokenizer.nextToken());
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());
            lines[i] = new Line(new Point(x1, y1), new Point(x2, y2));
        }

        String answer;
        if (checkCCW(lines[0], lines[1])) {
            answer = "1";
        } else {
            answer = "0";
        }

        bw.write(answer + "");
        bw.flush();
        bw.close();
        br.close();
    }

    static boolean checkCCW(Line l1, Line l2) {
        Point p1 = l1.a;
        Point p2 = l1.b;
        Point p3 = l2.a;
        Point p4 = l2.b;

        int p1p2 = ccw(p1, p2, p3) * ccw(p1, p2, p4);
        int p3p4 = ccw(p3, p4, p1) * ccw(p3, p4, p2);

        if (p1p2 == 0 && p3p4 == 0) {
            // p4에 대한 p1의 상대 위치와 p2에 대한 p3의 상대 위치 비교
            return p1.compareTo(p4) <= 0 && p3.compareTo(p2) <= 0;
        }

        return p1p2 <= 0 && p3p4 <= 0;
    }

    static int ccw(Point p1, Point p2, Point p3) {
        // ccw 공식 : (x1y2 + x2y3 + x3y1) - (y1x2 + y2x3 + y3x1)
        long s = ((p1.x * p2.y) + (p2.x * p3.y) + (p3.x * p1.y)) - ((p1.y * p2.x + p2.y * p3.x + p3.y * p1.x));
        return Long.compare(s, 0);
    }

    static class Line {
        Point a, b;

        public Line(Point a, Point b) {
            this.a = a.compareTo(b) <= 0 ? a : b;
            this.b = a.compareTo(b) <= 0 ? b : a;
        }
    }

    static class Point implements Comparable<Point> {
        long x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) {
                return (int) (this.y - o.y);
            }
            return (int) (this.x - o.x);
        }
    }
}