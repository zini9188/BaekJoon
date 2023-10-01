import java.io.*;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(tokenizer.nextToken());
            int y1 = Integer.parseInt(tokenizer.nextToken());
            int x2 = Integer.parseInt(tokenizer.nextToken());
            int y2 = Integer.parseInt(tokenizer.nextToken());

            int cnt = 0;
            int n = Integer.parseInt(br.readLine());
            for (int j = 0; j < n; j++) {
                tokenizer = new StringTokenizer(br.readLine());

                int cx = Integer.parseInt(tokenizer.nextToken());
                int cy = Integer.parseInt(tokenizer.nextToken());
                int r = Integer.parseInt(tokenizer.nextToken());
                boolean start = isInPlanetary(x1, y1, cx, cy, r);
                boolean dest = isInPlanetary(x2, y2, cx, cy, r);
                if (start && !dest || !start && dest) {
                    cnt++;
                }
            }
            sb.append(cnt).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    public static boolean isInPlanetary(int x, int y, int cx, int cy, int r) {
        return Math.pow(x - cx, 2) + Math.pow(y - cy, 2) <= r * r;
    }
}