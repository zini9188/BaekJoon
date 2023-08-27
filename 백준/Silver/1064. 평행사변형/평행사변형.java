import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();
    static ArrayList<Double> length;

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        Point a = new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        Point b = new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        Point c = new Point(Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
        
        if (isParallel(a, b, c)) {
            sb.append("-1.0");
        } else {
            length = new ArrayList<>();
            getPerimeter(a, b, c);
            getPerimeter(a, c, b);
            getPerimeter(b, c, a);
            length.sort(Comparator.comparingDouble(o -> o));
            sb.append(length.get(length.size() - 1) - length.get(0));
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static void getPerimeter(Point a, Point b, Point c) {
        double ab = a.getDist(b);
        double len1 = a.getDist(c);
        double len2 = b.getDist(c);
        length.add(ab * 2 + len1 * 2);
        length.add(ab * 2 + len2 * 2);
    }

    public static boolean isParallel(Point a, Point b, Point c) {
        return (b.y - a.y) * (c.x - b.x) == (b.x - a.x) * (c.y - b.y);
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public double getDist(Point other) {
            return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
        }
    }
}