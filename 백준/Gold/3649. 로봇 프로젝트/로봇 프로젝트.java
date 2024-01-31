import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        String input;

        while ((input = br.readLine()) != null) {
            int x = Integer.parseInt(input) * 10000000;

            int N = Integer.parseInt(br.readLine());

            List<Integer> block = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                block.add(Integer.parseInt(br.readLine()));
            }
            Collections.sort(block);
            sb.append(twoPointer(N, block, x)).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static String twoPointer(int N, List<Integer> block, int x) {
        int left = 0, right = N - 1;
        while (left < right) {
            int sum = block.get(right) + block.get(left);

            if (sum == x) {
                return String.format("yes %d %d", block.get(left), block.get(right));
            }

            if (sum > x) {
                right--;
            } else {
                left++;
            }
        }
        return "danger";
    }
}