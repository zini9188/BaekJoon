import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        tokenizer = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(tokenizer.nextToken());
        int b = Integer.parseInt(tokenizer.nextToken());
        int c = Integer.parseInt(tokenizer.nextToken());
        ArrayList<Integer> arr = new ArrayList<>(Arrays.asList(a, b, c));
        arr.sort(Comparator.comparingInt(o -> o));
        a = arr.get(0);
        b = arr.get(1);
        c = arr.get(2);

        if (a == b && b == c) {
            sb.append(a * 3);
        } else {
            if (a + b <= c) {
                sb.append(((a + b) + (a + b - 1)));
            } else {
                sb.append((a + b + c));
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
