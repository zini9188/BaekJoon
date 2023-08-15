import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int[] parents;
    static Line[] lines;
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        parents = new int[N];
        for (int i = 0; i < N; i++) {
            parents[i] = i;
        }

        lines = new Line[N];
        for (int i = 0; i < N; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(tokenizer.nextToken());
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());
            lines[i] = new Line(new Point(x1, y1), new Point(x2, y2));
        }

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (checkCCW(lines[i], lines[j])) {
                    union(i, j);
                }
            }
        }

        int[] cnt = new int[N];
        for (int i = 0; i < N; i++) {
            cnt[find(i)]++;
        }

        int lineCount = 0;
        int groupCount = 0;
        for (int i = 0; i < N; i++) {
            if (cnt[i] == 0) {
                continue;
            }
            lineCount++;
            groupCount = Math.max(groupCount, cnt[i]);
        }

        bw.write(lineCount + "\n" + groupCount);
        bw.flush();
        bw.close();
        br.close();
    }

    static void union(int i, int j) {
        i = find(i);
        j = find(j);
        if (i != j) {
            if (i < j) {
                parents[j] = i;
            } else {
                parents[i] = j;
            }
        }
    }

    static int find(int x) {
        if (parents[x] == x) {
            return x;
        }
        return parents[x] = find(parents[x]);
    }

    // https://jason9319.tistory.com/358 ccw, 선분 교차 판별에 대한 설명
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
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x == o.x) {
                return this.y - o.y;
            }
            return this.x - o.x;
        }
    }
}