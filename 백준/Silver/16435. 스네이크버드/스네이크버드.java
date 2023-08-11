import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(tokenizer.nextToken());
        int L = Integer.parseInt(tokenizer.nextToken());

        int[] fruits = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            fruits[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(fruits);

        for (int fruit : fruits) {
            if (fruit <= L) {
                L++;
            } else {
                break;
            }
        }

        bw.write(L + "");
        bw.flush();
        bw.close();
        br.close();
    }
}