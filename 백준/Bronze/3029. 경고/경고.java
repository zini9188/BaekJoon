import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String current = br.readLine();
        String throwTime = br.readLine();
        int t1 = StoI(current);
        int t2 = StoI(throwTime);
        if (t1 > t2) {
            t2 += 60 * 60 * 24;
        }
        if (t1 == t2) {
            sb.append("24:00:00");
        } else {
            sb.append(ItoS(t2 - t1));
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int StoI(String time) {
        tokenizer = new StringTokenizer(time, ":");
        int hour = Integer.parseInt(tokenizer.nextToken());
        int min = Integer.parseInt(tokenizer.nextToken());
        int sec = Integer.parseInt(tokenizer.nextToken());
        return hour * 60 * 60 + min * 60 + sec;
    }

    private static String ItoS(int time) {
        int hour = time / 3600;
        int min = time / 60 % 60;
        int sec = time % 60 % 60;
        return String.format("%02d:%02d:%02d", hour, min, sec);
    }
}