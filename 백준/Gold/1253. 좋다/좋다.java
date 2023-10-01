import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }
        Arrays.sort(arr);

        int cnt = 0;
        for (int i = 0; i < N; i++) {
            int left = 0, right = N - 1;
            while (left < right) {
                long sum = arr[left] + arr[right];
                if (left == i) {
                    left++;
                    continue;
                } else if(right == i){
                    right--;
                    continue;
                }

                if (sum > arr[i]) {
                    right--;
                } else if (sum < arr[i]) {
                    left++;
                } else {
                    cnt++;
                    break;
                }
            }
        }

        sb.append(cnt);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}