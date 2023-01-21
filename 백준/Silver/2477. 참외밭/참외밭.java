import java.io.*;
import java.util.*;

public class Main {
    static int K;
    static ArrayList<Integer> inputs;
    static int verticalMax = 0, horizontalMax = 0, verticalMaxIdx, horizontalMaxIdx;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        K = Integer.parseInt(br.readLine());
        StringTokenizer tokenizer;

        inputs = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tokenizer = new StringTokenizer(br.readLine());
            int direction = Integer.parseInt(tokenizer.nextToken());
            int length = Integer.parseInt(tokenizer.nextToken());
            inputs.add(length);
            if (direction == 1 || direction == 2) {
                if (horizontalMax < length) {
                    horizontalMax = length;
                    horizontalMaxIdx = i;
                }
            } else {
                if (verticalMax < length) {
                    verticalMax = length;
                    verticalMaxIdx = i;
                }
            }
        }
        int maxSquareArea = horizontalMax * verticalMax;
        int minSquareArea = inputs.get((horizontalMaxIdx + 3) % 6) * inputs.get((verticalMaxIdx + 3) % 6);
        bw.write((maxSquareArea - minSquareArea) * K + "");
        bw.flush();
        bw.close();
        br.close();
    }
}