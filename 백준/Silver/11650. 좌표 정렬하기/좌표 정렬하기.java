import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return x + " " + y;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());
        ArrayList<Point> points = new ArrayList<>();

        while (T-- > 0) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            int X = Integer.parseInt(tokenizer.nextToken());
            int Y = Integer.parseInt(tokenizer.nextToken());

            points.add(new Point(X, Y));
        }

        points.sort((a, b) -> a.getX() == b.getX() ? a.getY() - b.getY() : a.getX() - b.getX());
        for (Point point : points) {
            bw.write(point + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
