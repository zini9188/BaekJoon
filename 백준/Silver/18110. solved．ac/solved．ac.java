import java.io.*;
import java.util.Arrays;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        int n = Integer.parseInt(br.readLine());

        int[] difficulty = new int[n];
        for (int i = 0; i < n; i++) {
            difficulty[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(difficulty);

        int limit = (int) Math.round(n * 0.15);
        int sum = 0;
        for (int i = limit; i < n - limit; i++) {
            sum += difficulty[i];
        }
        
        bw.write(Math.round((double) sum / (n - limit * 2)) + "");
        bw.flush();
        bw.close();
        br.close();
    }
}