import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static long w, h, f, c, x1, x2, y1, y2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        w = Long.parseLong(tokenizer.nextToken());
        h = Long.parseLong(tokenizer.nextToken());
        f = Long.parseLong(tokenizer.nextToken());
        c = Long.parseLong(tokenizer.nextToken());
        x1 = Long.parseLong(tokenizer.nextToken());
        y1 = Long.parseLong(tokenizer.nextToken());
        x2 = Long.parseLong(tokenizer.nextToken());
        y2 = Long.parseLong(tokenizer.nextToken());

        long area = w * h;
        long painted = getPainted();
        bw.write((area - painted) + "");
        bw.flush();
        bw.close();
        br.close();
    }

    private static long getPainted() {
        long baseLine = Math.min(w - f, f);
        long rangeX;
        if (x1 >= baseLine) {
            rangeX = x2 - x1;
        } else if (x2 > baseLine) {
            rangeX = baseLine + x2 - (2 * x1);
        } else {
            rangeX = (x2 - x1) * 2;
        }
        return rangeX * (y2 - y1) * (c + 1);
    }
}