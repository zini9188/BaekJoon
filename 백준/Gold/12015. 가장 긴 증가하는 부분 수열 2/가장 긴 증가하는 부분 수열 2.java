import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        tokenizer = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(tokenizer.nextToken());
        }

        int[] LIS = new int[N];
        LIS[0] = arr[0];
        int lastIndex = 0;
        for (int i = 1; i < N; i++) {
            if (LIS[lastIndex] < arr[i]) {
                LIS[++lastIndex] = arr[i];
            } else {
                int left = 0, right = lastIndex;
                while (left < right) {
                    int mid = (left + right) / 2;

                    if (LIS[mid] < arr[i]) {
                        left = mid + 1;
                    } else {
                        right = mid;
                    }
                }
                LIS[left] = arr[i];
            }
        }
        
        bw.write((lastIndex + 1) + " ");
        bw.close();
        br.close();
    }
}