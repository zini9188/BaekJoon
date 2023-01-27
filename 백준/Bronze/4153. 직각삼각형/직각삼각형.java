import java.io.*;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int a, b, c;
        StringBuilder builder = new StringBuilder();
        while (true) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            a = Integer.parseInt(tokenizer.nextToken());
            b = Integer.parseInt(tokenizer.nextToken());
            c = Integer.parseInt(tokenizer.nextToken());
            if (a == 0 && b == 0 && c == 0) {
                break;
            }
            builder.append(findTriangle(a, b, c)).append("\n");
        }
        bw.write(builder.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    private static String findTriangle(int a, int b, int c) {
        if ((a * a == b * b + c * c) || (b * b == c * c + a * a) || (c * c == b * b + a * a)) {
            return "right";
        }
        return "wrong";
    }
}